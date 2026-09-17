package com.taushif.evidence.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogger {

    private static final String LOG_FILE = "data/audit.log";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(
            String username,
            String role,
            String action,
            String status) {

        File file = new File(LOG_FILE);

        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        String timestamp =
                LocalDateTime.now().format(FORMATTER);

        String logEntry =
                timestamp
                        + " | " + username
                        + " | " + role
                        + " | " + action
                        + " | " + status
                        + System.lineSeparator();

        try (FileWriter writer =
                     new FileWriter(file, true)) {

            writer.write(logEntry);

        } catch (IOException e) {

            System.out.println(
                    "Warning: Unable to write audit log."
            );
        }
    }
}