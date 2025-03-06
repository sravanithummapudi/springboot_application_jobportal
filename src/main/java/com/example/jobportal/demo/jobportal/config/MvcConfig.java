package com.example.jobportal.demo.jobportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
//WebMvc configuaration will map requests for photos to serve from a directory on our file system
public class MvcConfig implements WebMvcConfigurer {
    private static final String UPLOAD_DIR = "photos";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(UPLOAD_DIR,registry);
    }

    private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry) {
        Path path= Paths.get(UPLOAD_DIR);
        //this sets up a request path pattern that will match any request
        // starting with /photos/. The /** means any subpath of /photos/, so this will match
        // requests like /photos/someimage.jpg
        //files matching the path pattern (/photos/**) should be served from the actual file
        // system path specified. The file: prefix indicates that the resource
        // should be retrieved from the file system,
        registry.addResourceHandler("/"+uploadDir+"/**").addResourceLocations("file:"+path.toAbsolutePath()+"/");
    }
}
