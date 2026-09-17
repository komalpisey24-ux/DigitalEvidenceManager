package com.taushif.evidence;

import com.taushif.evidence.service.AuthorizationService;
import com.taushif.evidence.util.AuditLogger;
import com.taushif.evidence.service.EvidenceService;
import com.taushif.evidence.model.User;
import com.taushif.evidence.service.AuthService;
import com.taushif.evidence.service.CaseService;
import com.taushif.evidence.service.CustodyService;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AuthService authService = new AuthService();
        CaseService caseService = new CaseService();
        EvidenceService evidenceService = new EvidenceService();
        CustodyService custodyService = new CustodyService();
        AuthorizationService authorizationService =
                new AuthorizationService();

        System.out.println("======================================");
        System.out.println("     DIGITAL EVIDENCE MANAGER");
        System.out.println("======================================");



        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loggedInUser = authService.login(username, password);

        if (loggedInUser == null) {

            System.out.println("\nInvalid username or password.");

            AuditLogger.log(
                    username,
                    "UNKNOWN",
                    "LOGIN",
                    "FAILED"
            );

            scanner.close();
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + loggedInUser.getUsername());
        System.out.println("Role: " + loggedInUser.getRole());
        AuditLogger.log(
                loggedInUser.getUsername(),
                loggedInUser.getRole(),
                "LOGIN",
                "SUCCESS"
        );



        while (true) {

            System.out.println("\n======================================");
            System.out.println("              MAIN MENU");
            System.out.println("======================================");

            System.out.println("Logged in as: "
                    + loggedInUser.getUsername()
                    + " (" + loggedInUser.getRole() + ")");

            System.out.println("--------------------------------------");

            System.out.println("1. Create Case");
            System.out.println("2. View Cases");
            System.out.println("3. Register Evidence");
            System.out.println("4. View Evidence");
            System.out.println("5. Verify Evidence Integrity");
            System.out.println("6. Transfer Evidence");
            System.out.println("7. View Chain of Custody");
            System.out.println("8. Search Evidence");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {



                case "1":



                    if (!authorizationService.canCreateCase(loggedInUser)) {

                        System.out.println(
                                "\nAccess Denied: You do not have permission to create cases."
                        );

                        AuditLogger.log(
                                loggedInUser.getUsername(),
                                loggedInUser.getRole(),
                                "CREATE_CASE",
                                "ACCESS_DENIED"
                        );

                        break;
                    }



                    System.out.println(
                            "\n========== CREATE CASE =========="
                    );

                    System.out.print("Enter Case ID: ");
                    String caseId = scanner.nextLine().trim();

                    System.out.print("Enter Case Name: ");
                    String caseName = scanner.nextLine().trim();

                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine().trim();

                    System.out.print("Enter Investigator: ");
                    String investigator = scanner.nextLine().trim();



                    if (caseId.isEmpty()
                            || caseName.isEmpty()
                            || description.isEmpty()
                            || investigator.isEmpty()) {

                        System.out.println(
                                "\nError: All fields are required."
                        );

                        break;
                    }

                    // ==========================================
                    // SAVE CASE
                    // ==========================================

                    caseService.createCase(
                            caseId,
                            caseName,
                            description,
                            investigator
                    );

                    // ==========================================
                    // AUDIT LOG
                    // ==========================================

                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "CREATE_CASE: " + caseId,
                            "SUCCESS"
                    );

                    break;

                // ==========================================
                // 2. VIEW CASES
                // ==========================================

                case "2":


                    if (!authorizationService.canViewCases(loggedInUser)) {

                        System.out.println("\nAccess Denied.");

                        AuditLogger.log(
                                loggedInUser.getUsername(),
                                loggedInUser.getRole(),
                                "VIEW_CASES",
                                "ACCESS_DENIED"
                        );

                        break;
                    }

                    caseService.viewCases();

                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "VIEW_CASES",
                            "SUCCESS"
                    );

                    break;



                case "3":

                    if (!authorizationService.canRegisterEvidence(
                            loggedInUser)) {

                        System.out.println(
                                "\nAccess Denied: You do not have permission to register evidence."
                        );
                        AuditLogger.log(
                                loggedInUser.getUsername(),
                                loggedInUser.getRole(),
                                "REGISTER_EVIDENCE",
                                "ACCESS_DENIED"
                        );

                        break;
                    }

                    System.out.println(
                            "\n========== REGISTER EVIDENCE =========="
                    );

                    System.out.print("Evidence ID: ");
                    String evidenceId = scanner.nextLine();

                    System.out.print("Case ID: ");
                    String evidenceCaseId = scanner.nextLine();

                    System.out.print("Evidence Name: ");
                    String evidenceName = scanner.nextLine();

                    System.out.print("File Path: ");
                    String filePath = scanner.nextLine();

                    System.out.print("Collected By: ");
                    String collectedBy = scanner.nextLine();

                    evidenceService.registerEvidence(
                            evidenceId,
                            evidenceCaseId,
                            evidenceName,
                            filePath,
                            collectedBy
                    );
                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "REGISTER_EVIDENCE: " + evidenceId,
                            "SUCCESS"
                    );

                    break;



                case "4":

                    if (!authorizationService.canViewEvidence(
                            loggedInUser)) {

                        System.out.println(
                                "\nAccess Denied."
                        );

                        break;
                    }

                    evidenceService.viewEvidence();

                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "VIEW_EVIDENCE",
                            "SUCCESS"
                    );
                    break;



                case "5":

                    if (!authorizationService.canVerifyIntegrity(
                            loggedInUser)) {

                        System.out.println(
                                "\nAccess Denied."
                        );

                        break;
                    }

                    System.out.print(
                            "\nEnter Evidence ID: "
                    );

                    String verifyId = scanner.nextLine();

                    evidenceService.verifyIntegrity(verifyId);
                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "VERIFY_INTEGRITY: " + verifyId,
                            "SUCCESS"
                    );

                    break;


                case "6":

                    if (!authorizationService.canTransferEvidence(
                            loggedInUser)) {

                        System.out.println(
                                "\nAccess Denied: You do not have permission to transfer evidence."
                        );

                        break;
                    }

                    System.out.println(
                            "\n========== TRANSFER EVIDENCE =========="
                    );

                    System.out.print("Enter Evidence ID: ");
                    String transferEvidenceId =
                            scanner.nextLine();

                    System.out.print("From User: ");
                    String fromUser =
                            scanner.nextLine();

                    System.out.print("To User: ");
                    String toUser =
                            scanner.nextLine();

                    System.out.print("Enter transfer remarks: ");
                    String remarks =
                            scanner.nextLine();

                    custodyService.transferEvidence(
                            transferEvidenceId,
                            fromUser,
                            toUser,
                            remarks
                    );
                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "TRANSFER_EVIDENCE: " + transferEvidenceId
                                    + " FROM: " + fromUser
                                    + " TO: " + toUser,
                            "SUCCESS"
                    );

                    break;



                case "7":

                    if (!authorizationService.canViewCustody(
                            loggedInUser)) {

                        System.out.println(
                                "\nAccess Denied."
                        );

                        break;
                    }

                    System.out.print(
                            "\nEnter Evidence ID: "
                    );

                    String historyEvidenceId =
                            scanner.nextLine();

                    custodyService.viewCustodyHistory(
                            historyEvidenceId
                    );
                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "VIEW_CUSTODY: " + historyEvidenceId,
                            "SUCCESS"
                    );

                    break;


                case "8":

                    if (!authorizationService.canViewEvidence(loggedInUser)) {

                        System.out.println("\nAccess Denied.");

                        AuditLogger.log(
                                loggedInUser.getUsername(),
                                loggedInUser.getRole(),
                                "SEARCH_EVIDENCE",
                                "ACCESS_DENIED"
                        );

                        break;
                    }

                    System.out.println("\n========== SEARCH EVIDENCE ==========");
                    System.out.print(
                            "Enter Evidence ID, Case ID, or Evidence Name: "
                    );

                    String searchKeyword = scanner.nextLine();

                    evidenceService.searchEvidence(searchKeyword);

                    AuditLogger.log(
                            loggedInUser.getUsername(),
                            loggedInUser.getRole(),
                            "SEARCH_EVIDENCE: " + searchKeyword,
                            "SUCCESS"
                    );

                    break;
                case "9":

                    System.out.println(
                            "\nExiting application..."
                    );

                    scanner.close();

                    return;



                default:

                    System.out.println(
                            "\nInvalid choice. Please try again."
                    );
            }
        }
    }
}