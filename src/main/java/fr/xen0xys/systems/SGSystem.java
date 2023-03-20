package fr.xen0xys.systems;

import fr.xen0xys.models.fs.SGFile;
import fr.xen0xys.models.fs.SGFolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SGSystem {
    private final SGFolder parentFolder;
    private final List<SGFolder> folders;
    private final List<SGFile> files;

    public SGSystem(@NotNull final SGFolder parentFolder){
        this.parentFolder = parentFolder;
        this.folders = new ArrayList<>();
        this.files = new ArrayList<>();
        this.populateBackupFiles();
    }

    private void populateBackupFiles(){
        List<SGFolder> untreatedFolder = new ArrayList<>(Collections.singleton(this.parentFolder));
        while(untreatedFolder.size() > 0){
            SGFolder folder = untreatedFolder.get(0);
            untreatedFolder.remove(0);
            this.folders.add(folder);
            this.files.addAll(folder.listFiles());
            untreatedFolder.addAll(folder.listFolders());
        }
    }

    public SGFolder getParentFolder() {
        return parentFolder;
    }

    public List<SGFile> getFiles() {
        return files;
    }

    public List<SGFolder> getFolders() {
        return folders;
    }
}
