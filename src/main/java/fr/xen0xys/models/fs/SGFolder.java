package fr.xen0xys.models.fs;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class SGFolder extends SGFileSystem{

    public SGFolder(@NotNull final File file) {
        super(file);
        if(!file.isDirectory())
            throw new IllegalArgumentException("The given file is not a directory");
    }

    public List<SGFolder> listFolders(){
        return Stream.of(Objects.requireNonNull(this.getFile().listFiles(File::isDirectory))).map(SGFolder::new).toList();
    }

    public List<SGFile> listFiles(){
        return Stream.of(Objects.requireNonNull(this.getFile().listFiles(File::isFile))).map(SGFile::new).toList();
    }

    public String getAbsolutePath(){
        return this.getFile().getAbsolutePath();
    }

    public String getRelativePath(@NotNull final SGFolder folder){
        return this.getFile().getAbsolutePath().replace(folder.getAbsolutePath(), "");
    }

    public File getFolder() {
        return this.getFile();
    }
}
