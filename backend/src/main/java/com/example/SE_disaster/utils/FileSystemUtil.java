package com.example.SE_disaster.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSystemUtil {
    static public byte[] readFile(String filePath) {
        try (FileInputStream fread = new FileInputStream(filePath)){
            return fread.readAllBytes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public int writeFile(String filePath, byte[] data) {
        try (FileOutputStream fout = new FileOutputStream(filePath)){
            fout.write(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
