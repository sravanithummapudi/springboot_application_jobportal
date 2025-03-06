package com.example.jobportal.demo.jobportal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {


    public static void saveFile(String uploadDir, String filename, MultipartFile multipartFile) throws IOException{
        //convert upload directory path to path object
        Path uploadPath = Paths.get(uploadDir);
        //Create the directory if it doesn't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
      //  InputStream reads the file byte by byte or in chunks, rather than all at once,
        try (InputStream inputStream = multipartFile.getInputStream();) {
            Path path = uploadPath.resolve(filename);//Create a path for the file to be saved
            System.out.println("FilePath " + path);
            System.out.println("fileName " + filename);
            //Copy the file from the InputStream to the destination path
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);//overwrites if file exists already
        }
        catch (IOException ioe) {
            throw new IOException("Could not save image file: " + filename, ioe);

        }
    }

}
