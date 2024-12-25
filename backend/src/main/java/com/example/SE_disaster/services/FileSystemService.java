package com.example.SE_disaster.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Service
public class FileSystemService {
    @Value("${server.upload.path}")
    static private String uploadPath;

    static public byte[] readFile(String filePath) {
        try (FileInputStream fread = new FileInputStream(uploadPath + filePath)){
            return fread.readAllBytes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void writeFile(String filePath, byte[] data) {
        try (FileOutputStream fwrite = new FileOutputStream(uploadPath + filePath)){
            fwrite.write(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
