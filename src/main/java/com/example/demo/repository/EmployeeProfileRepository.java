// package com.example.demo.repository;
// import com.example.demo.model.EmployeeProfile;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {

//     Optional<EmployeeProfile> findByEmployeeId(String employeeId);

//     Optional<EmployeeProfile> findByEmail(String email);
// }

package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
    Optional<EmployeeProfile> findByEmail(String email);
}
