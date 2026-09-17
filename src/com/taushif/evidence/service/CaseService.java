package com.taushif.evidence.service;

import com.taushif.evidence.model.CaseFile;
import com.taushif.evidence.repository.DataStore;





import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CaseService {

    private static final String FILE_PATH = "data/cases.dat";

    private List<CaseFile> cases;

    public CaseService() {
        cases = loadCases();
    }

    // ==========================================
    // CREATE CASE
    // ==========================================

    public void createCase(
            String caseId,
            String caseName,
            String description,
            String investigator) {

        // Check duplicate Case ID
        for (CaseFile existingCase : cases) {

            if (existingCase.getCaseId().equalsIgnoreCase(caseId)) {

                System.out.println(
                        "\nError: Case ID already exists."
                );

                return;
            }
        }

        CaseFile newCase = new CaseFile(
                caseId,
                caseName,
                description,
                investigator
        );

        cases.add(newCase);

        saveCases();

        System.out.println(
                "\n✓ Case created successfully!"
        );

        System.out.println(
                "Case ID: " + caseId
        );
    }

    // ==========================================
    // VIEW CASES
    // ==========================================

    public void viewCases() {

        System.out.println(
                "\n========== ALL CASES =========="
        );

        if (cases.isEmpty()) {

            System.out.println(
                    "No cases found."
            );

            return;
        }

        for (CaseFile caseFile : cases) {

            System.out.println(
                    "Case ID: " + caseFile.getCaseId()
            );

            System.out.println(
                    "Case Name: " + caseFile.getCaseName()
            );

            System.out.println(
                    "Description: " + caseFile.getDescription()
            );

            System.out.println(
                    "Investigator: " + caseFile.getInvestigator()
            );

            System.out.println(
                    "-------------------------------"
            );
        }
    }

    // ==========================================
    // SAVE CASES
    // ==========================================

    private void saveCases() {

        File directory = new File("data");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(FILE_PATH)
                        )
        ) {

            output.writeObject(cases);

        } catch (IOException e) {

            System.out.println(
                    "Error saving cases: "
                            + e.getMessage()
            );
        }
    }

    // ==========================================
    // LOAD CASES
    // ==========================================

    @SuppressWarnings("unchecked")
    private List<CaseFile> loadCases() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(FILE_PATH)
                        )
        ) {

            return (List<CaseFile>) input.readObject();

        } catch (Exception e) {

            System.out.println(
                    "Warning: Could not load cases."
            );

            return new ArrayList<>();
        }
    }
}