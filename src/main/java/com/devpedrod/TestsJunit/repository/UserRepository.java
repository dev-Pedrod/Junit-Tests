package com.devpedrod.TestsJunit.repository;

import com.devpedrod.TestsJunit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
