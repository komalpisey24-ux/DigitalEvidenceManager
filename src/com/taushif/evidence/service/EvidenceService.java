package com.taushif.evidence.service;

import com.taushif.evidence.model.Evidence;
import com.taushif.evidence.repository.DataStore;
import com.taushif.evidence.model.CaseFile;
import java.util.List;

import java.util.ArrayList;

public class EvidenceService {


    private static final String FILE_PATH =
            "data/evidence.dat";

    private List<Evidence> evidenceList;
    private boolean caseExists(String caseId) {

        List<CaseFile> cases = DataStore.load("data/cases.dat");

        if (cases == null) {
            return false;
        }

        for (CaseFile c : cases) {
            if (c.getCaseId().equals(caseId)) {
                return true;
            }
        }

        return false;
    }

    private final HashService hashService =
            new HashService();

    public EvidenceService() {

        Object data = DataStore.load(FILE_PATH);

        if (data instanceof List<?>) {
            evidenceList = (List<Evidence>) data;
        } else {
            evidenceList = new ArrayList<>();
        }
    }
    public void searchEvidence(String keyword) {

        if (evidenceList == null || evidenceList.isEmpty()) {
            System.out.println("\nNo evidence records found.");
            return;
        }

        boolean found = false;

        System.out.println("\n========== SEARCH RESULTS ==========");

        for (Evidence e : evidenceList) {

            if (e.getEvidenceId().equalsIgnoreCase(keyword)
                    || e.getCaseId().equalsIgnoreCase(keyword)
                    || e.getEvidenceName().toLowerCase().contains(keyword.toLowerCase())) {

                System.out.println("--------------------------------------");
                System.out.println("Evidence ID   : " + e.getEvidenceId());
                System.out.println("Case ID       : " + e.getCaseId());
                System.out.println("Evidence Name : " + e.getEvidenceName());
                System.out.println("File Path     : " + e.getFilePath());
                System.out.println("Collected By  : " + e.getCollectedBy());
                System.out.println("SHA-256       : " + e.getHash());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching evidence found.");
        }
    }

    public void registerEvidence(
            String evidenceId,
            String caseId,
            String evidenceName,
            String filePath,
            String collectedBy) {
        if (!caseExists(caseId)) {
            System.out.println("Error: Case ID does not exist.");
            System.out.println("Please create the case first.");
            return;
        }

        // Check duplicate Evidence ID
        for (Evidence evidence : evidenceList) {

            if (evidence.getEvidenceId()
                    .equalsIgnoreCase(evidenceId)) {

                System.out.println(
                        "\nError: Evidence ID already exists."
                );

                return;
            }
        }

        try {

            String hash =
                    hashService.calculateSHA256(filePath);

            Evidence evidence =
                    new Evidence(
                            evidenceId,
                            caseId,
                            evidenceName,
                            filePath,
                            hash,
                            collectedBy
                    );

            evidenceList.add(evidence);

            DataStore.save("data/evidence.dat", evidenceList);

            System.out.println(
                    "\nEvidence registered successfully!"
            );

            System.out.println(
                    "Evidence ID: " + evidenceId
            );

            System.out.println(
                    "SHA-256 Hash: " + hash
            );

        } catch (Exception e) {

            System.out.println(
                    "\nError calculating file hash: "
                            + e.getMessage()
            );
        }
    }

    public void viewEvidence() {

        if (evidenceList.isEmpty()) {

            System.out.println(
                    "\nNo evidence registered."
            );

            return;
        }

        System.out.println(
                "\n========== EVIDENCE =========="
        );

        for (Evidence evidence : evidenceList) {

            System.out.println(evidence);

            System.out.println(
                    "-------------------------------"
            );
        }
    }

    public void verifyIntegrity(
            String evidenceId) {

        for (Evidence evidence : evidenceList) {

            if (evidence.getEvidenceId()
                    .equalsIgnoreCase(evidenceId)) {

                try {

                    String currentHash =
                            hashService.calculateSHA256(
                                    evidence.getFilePath()
                            );

                    System.out.println(
                            "\n========== INTEGRITY CHECK =========="
                    );

                    System.out.println(
                            "Original SHA-256: "
                                    + evidence.getHash()
                    );

                    System.out.println(
                            "Current SHA-256:  "
                                    + currentHash
                    );

                    if (evidence.getHash()
                            .equals(currentHash)) {

                        System.out.println(
                                "\n✓ INTEGRITY VERIFIED"
                        );

                    } else {

                        System.out.println(
                                "\n✗ INTEGRITY VIOLATION DETECTED"
                        );
                    }

                } catch (Exception e) {

                    System.out.println(
                            "\nError verifying evidence: "
                                    + e.getMessage()
                    );
                }

                return;
            }
        }

        System.out.println(
                "\nEvidence not found."
        );
    }
}