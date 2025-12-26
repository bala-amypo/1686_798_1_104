// package com.example.demo.repository;

// import com.example.demo.model.UserAccount;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

//     Optional<UserAccount> findByEmail(String email);
// }


package com.example.demo.repository;

import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}

