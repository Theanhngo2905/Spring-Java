package com.devteria.indentity_service.repository;

import com.devteria.indentity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Spring Data JPA sẽ tự động tạo truy vấn cho phương thức này
    Optional<User> findByUsername(String username);

     List<User> findByFirstName(String firstName);
     List<User> findByLastNameAndFirstName(String lastName, String firstName);
     Boolean existsByUsername(String username);
}
