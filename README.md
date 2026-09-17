# Digital Evidence Chain-of-Custody Manager

## Overview

Digital Evidence Chain-of-Custody Manager is a Java-based command-line
application designed to manage digital evidence during an investigation.

The system allows authorized users to create investigation cases,
register digital evidence, calculate SHA-256 hashes, verify evidence
integrity, transfer evidence between users, maintain chain-of-custody
records, search evidence, and maintain audit logs.

## Objectives

- Manage digital investigation cases
- Register and track digital evidence
- Maintain evidence chain of custody
- Verify evidence integrity using SHA-256
- Implement authentication and role-based authorization
- Maintain an audit trail of important activities
- Provide persistent local data storage

## Features

### 1. User Authentication

Users must log in using a username and password.

### 2. Case Management

Authorized users can create and view investigation cases.

### 3. Evidence Management

Evidence can be registered against an existing case.

### 4. Evidence Integrity Verification

The application calculates a SHA-256 hash when evidence is registered.
The hash can later be recalculated to detect modification.

### 5. Chain of Custody

Evidence transfers are recorded with:

- Evidence ID
- From user
- To user
- Transfer action
- Remarks

### 6. Evidence Search

Evidence can be searched using:

- Evidence ID
- Case ID
- Evidence name

### 7. Role-Based Authorization

Different operations are controlled according to user roles.

Supported roles:

- ADMIN
- INVESTIGATOR
- EVIDENCE_OFFICER

### 8. Audit Logging

Important system activities are recorded with:

- Timestamp
- Username
- Role
- Action
- Status

## Technologies

- Java
- Object-Oriented Programming
- Java Collections Framework
- Java Serialization
- SHA-256
- Exception Handling
- File I/O
- Git and GitHub

## Project Structure

```text
DigitalEvidenceManager/
│
├── src/
│   └── com/taushif/evidence/
│       ├── Main.java
│       │
│       ├── model/
│       │   ├── User.java
│       │   ├── CaseFile.java
│       │   ├── Evidence.java
│       │   └── CustodyRecord.java
│       │
│       ├── service/
│       │   ├── AuthService.java
│       │   ├── AuthorizationService.java
│       │   ├── CaseService.java
│       │   ├── EvidenceService.java
│       │   ├── CustodyService.java
│       │   └── HashService.java
│       │
│       ├── repository/
│       │   └── DataStore.java
│       │
│       ├── exception/
│       │   ├── EvidenceNotFoundException.java
│       │   ├── UnauthorizedException.java
│       │   └── IntegrityViolationException.java
│       │
│       └── util/
│           └── AuditLogger.java
│
├── data/
├── TESTING.md
├── statement.md
├── README.md
└── .gitignore
