package com.example.jobportal.demo.jobportal.util;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
//create path
    private Path foundfile;
//create method as resource to get file
    public Resource getFileAsResourse(String downloadDir, String fileName) throws IOException {
//create object to work with file operations in java
        Path path = Paths.get(downloadDir);
        //Files.list(path) method lists all the files in the specified directory.
        Files.list(path).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileName)) {// Matches files that start with fileName
                foundfile = file;//
            }
        });
// If a file is found, return it as a Resource (which can be used for downloading)
        if (foundfile != null) {
            return new UrlResource(foundfile.toUri());
        }
        return null;
    }
}