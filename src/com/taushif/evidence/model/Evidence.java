package com.taushif.evidence.model;

import java.io.Serializable;

public class Evidence implements Serializable {

    private String evidenceId;
    private String caseId;
    private String evidenceName;
    private String filePath;
    private String hash;
    private String collectedBy;

    public Evidence(String evidenceId,
                    String caseId,
                    String evidenceName,
                    String filePath,
                    String hash,
                    String collectedBy) {

        this.evidenceId = evidenceId;
        this.caseId = caseId;
        this.evidenceName = evidenceName;
        this.filePath = filePath;
        this.hash = hash;
        this.collectedBy = collectedBy;
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHash() {
        return hash;
    }

    public String getCollectedBy() {
        return collectedBy;
    }

    @Override
    public String toString() {
        return "\nEvidence ID: " + evidenceId +
                "\nCase ID: " + caseId +
                "\nEvidence Name: " + evidenceName +
                "\nFile Path: " + filePath +
                "\nSHA-256: " + hash +
                "\nCollected By: " + collectedBy;
    }
}