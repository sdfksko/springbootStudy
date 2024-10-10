package com.example.firstproject.dao.repository;

import com.example.firstproject.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
// findBy규칙 -> Username문법
// select * from user where username = 1?
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
