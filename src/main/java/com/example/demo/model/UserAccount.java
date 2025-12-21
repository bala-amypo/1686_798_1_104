STEP 0 - Technical Constraints (Critical for Testing)

You must strictly follow these technical rules to pass the automated test suite:

1. Dependency Injection:

You must use Constructor Injection for all Service classes. Do not use Field
Injection (@Autowired on fields). The test suite instantiates services
manually using constructors.

2. Custom Exceptions:

. Create com.example.demo.exception.BadRequestException (used for
validation errors).
Create com.example.demo.exception.ResourceNotFoundException (used
for missing entities).

3. Exception Messages:

. When throwing BadRequestException, your message must contain these
keywords:

Duplicate Employee ID: "Employeeld already exists"
Invalid Max Limit: "maxAllowedPerEmployee"
Device Already Returned: "already returned"
Duplicate Rule Code: "Rule code"
4. Repository Method Signatures (Exact Naming Required):
. EmployeeProfileRepository: findByEmployeeld(String employeeld)
. DeviceCatalogltemRepository: findByDeviceCode(String deviceCode)
. IssuedDeviceRecordRepository: countActiveDevicesForEmployee(Long
employeeld)
. IssuedDeviceRecordRepository: findActiveByEmployeeAndDevice(Long
employeeld, Long deviceltemld)
. PolicyRuleRepository: findByActiveTrue()
. PolicyRuleRepository: findByRuleCode(String ruleCode)
. EligibilityCheckRecordRepository: findByEmployeeld(Long employeeld)

Entity:
6. UserAccount

. Fields: id (Long, PK), fullName (String), email (String), passwordHash (String),
role (String: ADMIN / IT_OPERATOR / AUDITOR), active (Boolean)
Rules:

email must be unique.
Password must be hashed.

Repository:
UserAccountRepository

I