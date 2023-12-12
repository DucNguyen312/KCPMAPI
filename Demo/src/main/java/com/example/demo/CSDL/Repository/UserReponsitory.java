package com.example.demo.CSDL.Repository;

import com.example.demo.CSDL.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
    boolean existsByEmail(String email);

}
