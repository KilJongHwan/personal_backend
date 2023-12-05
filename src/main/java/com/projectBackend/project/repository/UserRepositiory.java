package com.projectBackend.project.repository;

import com.projectBackend.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositiory extends JpaRepository<User, Long> {
}
