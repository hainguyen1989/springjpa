package com.haint.springjpa.db;

import com.haint.springjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findUserByRolesId(Long roleId);
}
