package com.taushif.evidence.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CustodyRecord implements Serializable {

    private String evidenceId;
    private String fromUser;
    private String toUser;
    private String action;
    private String remarks;
    private LocalDateTime timestamp;

    public CustodyRecord(String evidenceId,
                         String fromUser,
                         String toUser,
                         String action,
                         String remarks) {

        this.evidenceId = evidenceId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.action = action;
        this.remarks = remarks;
        this.timestamp = LocalDateTime.now();
    }

    public String getEvidenceId() {
        return evidenceId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public String getAction() {
        return action;
    }

    public String getRemarks() {
        return remarks;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "\nEvidence ID : " + evidenceId +
                "\nFrom        : " + fromUser +
                "\nTo          : " + toUser +
                "\nAction      : " + action +
                "\nRemarks     : " + remarks +
                "\nTimestamp   : " + timestamp;
    }
}