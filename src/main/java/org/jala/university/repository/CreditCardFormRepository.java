package org.jala.university.repository;

import org.jala.university.model.CreditCardForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardFormRepository extends JpaRepository<CreditCardForm, UUID> {
}
