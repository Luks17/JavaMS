package com.msjava.email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msjava.email.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
