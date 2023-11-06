package org.jala.university.repository;

import org.jala.university.model.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<FormModel, UUID> {
    @Query(value = "SELECT COUNT(*) FROM credit_card", nativeQuery = true)
    int checkCard();
}
