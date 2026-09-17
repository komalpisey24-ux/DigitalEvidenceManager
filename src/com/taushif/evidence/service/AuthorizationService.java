package com.taushif.evidence.service;

import com.taushif.evidence.model.User;

public class AuthorizationService {

    public boolean canCreateCase(User user) {
        return user.getRole().equals("ADMIN")
                || user.getRole().equals("INVESTIGATOR");
    }

    public boolean canViewCases(User user) {
        return true;
    }

    public boolean canRegisterEvidence(User user) {
        return user.getRole().equals("ADMIN")
                || user.getRole().equals("INVESTIGATOR");
    }

    public boolean canViewEvidence(User user) {
        return true;
    }

    public boolean canVerifyIntegrity(User user) {
        return true;
    }

    public boolean canTransferEvidence(User user) {
        return user.getRole().equals("ADMIN")
                || user.getRole().equals("INVESTIGATOR")
                || user.getRole().equals("EVIDENCE_OFFICER");
    }

    public boolean canViewCustody(User user) {
        return true;
    }

    public boolean isAdmin(User user) {
        return user.getRole().equals("ADMIN");
    }
}
