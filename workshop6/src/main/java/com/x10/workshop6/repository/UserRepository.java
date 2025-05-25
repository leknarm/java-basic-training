package com.x10.workshop6.repository;

import com.x10.workshop6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
