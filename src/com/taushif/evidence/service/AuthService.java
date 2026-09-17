package com.taushif.evidence.service;

import com.taushif.evidence.model.User;

import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private final List<User> users = new ArrayList<>();

    public AuthService() {
        // Default users
        users.add(new User("admin", "admin123", "ADMIN"));
        users.add(new User("investigator", "invest123", "INVESTIGATOR"));
        users.add(new User("officer", "officer123", "EVIDENCE_OFFICER"));
    }

    public User login(String username, String password) {

        for (User user : users) {

            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }
}