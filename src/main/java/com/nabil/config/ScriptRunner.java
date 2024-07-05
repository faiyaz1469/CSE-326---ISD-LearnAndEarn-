package com.nabil.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ScriptRunner implements CommandLineRunner{
    @Autowired
    private ResourceLoader resourceLoader;
    
    @Override
    public void run(String... args) throws Exception {
        
        // final Resource fileResource = resourceLoader.getResource("classpath:scripts/main.y");
        // File script = fileResource.getFile();

        Process p = Runtime.getRuntime().exec("python resources/scripts/main.py");
        
    }
}
