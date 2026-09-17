# Testing and Validation

## 1. Testing Objective

The Digital Evidence Chain-of-Custody Manager was tested to verify
authentication, authorization, case management, evidence registration,
evidence integrity verification, chain-of-custody tracking, searching,
persistence, and audit logging.

## 2. Test Cases

| Test Case | Description | Expected Result | Status |
|-----------|-------------|-----------------|--------|
| TC01 | Valid Login | User successfully logs in | PASS |
| TC02 | Invalid Login | Access is rejected | PASS |
| TC03 | Create Case | New case is created | PASS |
| TC04 | Duplicate Case ID | Duplicate case is rejected | PASS |
| TC05 | Register Evidence | Evidence is registered with SHA-256 hash | PASS |
| TC06 | Invalid Case ID | Evidence registration is rejected | PASS |
| TC07 | Verify Untampered Evidence | Integrity verification succeeds | PASS |
| TC08 | Modify Evidence File | Integrity violation is detected | PASS |
| TC09 | Search Evidence | Evidence is found using search keyword | PASS |
| TC10 | Transfer Evidence | Custody transfer is recorded | PASS |
| TC11 | View Chain of Custody | Custody history is displayed | PASS |
| TC12 | Application Restart | Stored data remains available | PASS |

## 3. Security Testing

The application was tested for:

- Authentication using username and password
- Role-based authorization
- Evidence integrity using SHA-256
- Audit logging
- Invalid case ID validation
- Duplicate ID validation
- Invalid login handling

## 4. Persistence Testing

The application was closed and restarted after creating
cases and evidence. Previously stored records remained available,
confirming persistent storage.

## 5. Integrity Testing

A test evidence file was registered and its SHA-256 hash was stored.

After modifying the file, integrity verification detected the
difference between the stored hash and the newly calculated hash.

## 6. Result

All major functional modules were tested successfully.
The application correctly handled valid operations, invalid inputs,
evidence modification, authentication failures, and persistent data.
