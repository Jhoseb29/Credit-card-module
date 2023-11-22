package org.jala.university.repository;

import org.jala.university.model.CreditCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardModel, UUID> {
    @Query(value = "SSELECT COUNT(*) FROM creditcardmodule.credit_card")
    int checkCard();
}
