package com.example.fuzz.repository;

import com.example.fuzz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/23 16:32
 */
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
