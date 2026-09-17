package com.taushif.evidence.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class HashService {

    public String calculateSHA256(String filePath) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream inputStream = new FileInputStream(filePath)) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();

        StringBuilder hash = new StringBuilder();

        for (byte b : hashBytes) {
            hash.append(String.format("%02x", b));
        }

        return hash.toString();
    }
}