package org.jala.university.repository;

import org.jala.university.model.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardFormRepository extends JpaRepository<FormModel, UUID> {
    @Query
    public int checkCard();
}
