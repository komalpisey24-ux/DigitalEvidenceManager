package com.taushif.evidence.model;

import java.io.Serializable;

public class CaseFile implements Serializable {

    private String caseId;
    private String caseName;
    private String description;
    private String investigator;

    public CaseFile(String caseId, String caseName,
                    String description, String investigator) {

        this.caseId = caseId;
        this.caseName = caseName;
        this.description = description;
        this.investigator = investigator;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public String getDescription() {
        return description;
    }

    public String getInvestigator() {
        return investigator;
    }

    @Override
    public String toString() {
        return "\nCase ID: " + caseId +
                "\nCase Name: " + caseName +
                "\nDescription: " + description +
                "\nInvestigator: " + investigator;
    }
}