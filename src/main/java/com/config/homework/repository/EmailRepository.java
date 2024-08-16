package com.config.homework.repository;

import com.config.homework.model.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
    Boolean existsByEmail(String email);
}
