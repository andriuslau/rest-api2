package com.juniorjavadeveloper.restapidemo.repository;

import com.juniorjavadeveloper.restapidemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserNameIgnoreCase(String userName);
}
