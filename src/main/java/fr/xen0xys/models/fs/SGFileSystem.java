package fr.xen0xys.models.fs;

import java.io.File;

public abstract class SGFileSystem {

    private final File file;

    public SGFileSystem(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getAbsolutePath(){
        return this.file.getAbsolutePath();
    }

    public String getRelativePath(SGFolder folder){
        return this.file.getAbsolutePath().replace(folder.getAbsolutePath(), "");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SGFileSystem givenFile && givenFile.getAbsolutePath().equals(this.getAbsolutePath());
    }

}
