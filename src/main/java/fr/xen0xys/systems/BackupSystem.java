package fr.xen0xys.systems;

import fr.xen0xys.models.fs.SGFile;
import fr.xen0xys.models.fs.SGFileSystem;
import fr.xen0xys.models.fs.SGFolder;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BackupSystem extends SGSystem{

    private final SGFolder sourceFolder;

    public BackupSystem(@NotNull final SGFolder backupFolder, @NotNull final SGFolder sourceFolder){
        super(backupFolder);
        this.sourceFolder = sourceFolder;
    }

    public void sync(){
        List<SGFolder> untreatedFolder = new ArrayList<>(Collections.singleton(this.sourceFolder));
        List<SGFileSystem> backupFiles = new ArrayList<>(this.getFiles());
        backupFiles.addAll(this.getFolders());
        while(untreatedFolder.size() > 0){
            SGFolder folder = untreatedFolder.get(0);
            untreatedFolder.remove(0);
            untreatedFolder.addAll(folder.listFolders());
            System.out.println("Syncing folder: " + folder.getAbsolutePath());
            // Creating folder if not exists
            //noinspection ResultOfMethodCallIgnored
            folder.getFolder().mkdir();
            this.removeFile(backupFiles, folder);
            for(final SGFile file : folder.listFiles()){
                System.out.println("Syncing file: " + file.getFile().getAbsolutePath());
                File newFile = new File(this.getParentFolder().getAbsolutePath() + file.getRelativePath(this.sourceFolder));
                if(!newFile.exists()) { // Source file not backup
                    try {
                        FileUtils.copyFile(file.getFile(), newFile);
                        this.removeFile(backupFiles, file);
                        System.out.printf("File backup: %s%n", newFile.getAbsolutePath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{ // Source file already backup (version ?)
                    try {
                        if(!FileUtils.contentEquals(file.getFile(), newFile)){
                            if(!newFile.delete())
                                throw new RuntimeException("Can't delete file: " + newFile.getAbsolutePath());
                            FileUtils.copyFile(file.getFile(), newFile);
                            System.out.printf("File updated: %s%n", newFile.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.removeFile(backupFiles, file);
                }
            }
        }
        // Remove non-existing files on backup
        for(final SGFileSystem file : backupFiles){
            try {
                FileUtils.forceDelete(file.getFile());
                if(file instanceof SGFile)
                    System.out.println("File removed: " + file.getFile().getAbsolutePath());
                else
                    System.out.println("Folder removed: " + file.getFile().getAbsolutePath());
            } catch (FileNotFoundException ignored) {

            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void removeFile(@NotNull final List<SGFileSystem> backupFiles, @NotNull final SGFileSystem file){
        for(SGFileSystem backupFile : backupFiles)
            if(backupFile.getRelativePath(this.getParentFolder()).equals(file.getRelativePath(this.sourceFolder))){
                backupFiles.remove(backupFile);
                return;
            }
    }
}
