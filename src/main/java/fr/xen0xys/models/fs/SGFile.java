package fr.xen0xys.models.fs;

import java.io.File;

public class SGFile extends SGFileSystem{

    public SGFile(File file) {
        super(file);
        if(!file.isFile())
            throw new IllegalArgumentException("The given file is not a file");
    }
}
