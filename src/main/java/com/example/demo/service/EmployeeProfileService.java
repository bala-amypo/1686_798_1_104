// package com.example.demo.service;

// import com.example.demo.model.EmployeeProfile;
// import java.util.List;

// public interface EmployeeProfileService {

//     EmployeeProfile createEmployee(EmployeeProfile emp);

//     EmployeeProfile updateEmployeeStatus(Long id, boolean active);

//     EmployeeProfile getEmployeeById(Long id);

//     List<EmployeeProfile> getAllEmployees();
// }

// package com.example.demo.service;

// import com.example.demo.model.EmployeeProfile;
// import java.util.List;

// public interface EmployeeProfileService {
//     EmployeeProfile createEmployee(EmployeeProfile employee);
//     EmployeeProfile updateEmployeeStatus(Long id, Boolean active);
//     EmployeeProfile getEmployeeById(Long id);
//     List<EmployeeProfile> getAllEmployees();
// }

package com.example.demo.service;

import java.util.List;
import com.example.demo.model.EmployeeProfile;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfile employee);

    EmployeeProfile getEmployeeById(Long id);

    List<EmployeeProfile> getAllEmployees();

    EmployeeProfile updateEmployeeStatus(Long id, boolean active);

    void deleteEmployee(Long id);
}
