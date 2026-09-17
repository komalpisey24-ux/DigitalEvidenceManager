package com.taushif.evidence.repository;

import java.io.*;

public class DataStore {

    public static void save(String filePath, Object data) {
        try {
            File file = new File(filePath);

            // Create data directory if it doesn't exist
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            ObjectOutputStream output =
                    new ObjectOutputStream(new FileOutputStream(file));

            output.writeObject(data);
            output.close();

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T load(String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            return null;
        }

        try {
            ObjectInputStream input =
                    new ObjectInputStream(new FileInputStream(file));

            T data = (T) input.readObject();

            input.close();

            return data;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}