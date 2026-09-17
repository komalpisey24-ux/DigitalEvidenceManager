package com.taushif.evidence.service;

import com.taushif.evidence.model.CustodyRecord;
import com.taushif.evidence.repository.DataStore;

import java.util.ArrayList;
import java.util.List;

public class CustodyService {

    private static final String FILE_PATH =
            "data/custody.dat";

    private List<CustodyRecord> custodyRecords;

    public CustodyService() {

        Object data = DataStore.load(FILE_PATH);

        if (data instanceof List<?>) {
            custodyRecords =
                    (List<CustodyRecord>) data;
        } else {
            custodyRecords =
                    new ArrayList<>();
        }
    }

    public void transferEvidence(
            String evidenceId,
            String fromUser,
            String toUser,
            String remarks) {

        CustodyRecord record =
                new CustodyRecord(
                        evidenceId,
                        fromUser,
                        toUser,
                        "TRANSFER",
                        remarks
                );

        custodyRecords.add(record);

        DataStore.save(
                "data/custody.dat",
                custodyRecords
        );

        System.out.println(
                "\n================================="
        );

        System.out.println(
                "     EVIDENCE TRANSFERRED"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "Evidence ID : " + evidenceId
        );

        System.out.println(
                "From        : " + fromUser
        );

        System.out.println(
                "To          : " + toUser
        );

        System.out.println(
                "Remarks     : " + remarks
        );

        System.out.println(
                "================================="
        );
    }

    public void viewCustodyHistory(
            String evidenceId) {

        boolean found = false;

        System.out.println(
                "\n================================="
        );

        System.out.println(
                "       CHAIN OF CUSTODY"
        );

        System.out.println(
                "================================="
        );

        for (CustodyRecord record :
                custodyRecords) {

            if (record.getEvidenceId()
                    .equalsIgnoreCase(evidenceId)) {

                System.out.println(record);

                System.out.println(
                        "---------------------------------"
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No custody records found."
            );
        }
    }
}