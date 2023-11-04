package org.jala.university.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jala.university.dao.EntityDAO;

import java.util.UUID;

@Entity(name = "credit_card")
@Getter
@Setter
@Builder
public class CreditCardModel implements EntityDAO<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_credit_card")
    private UUID id;
    @Column
    private boolean approved_card;
    @Column
    private double credit_limit;
    @Column
    private double current_limit;
    @Column
    private String card;
    @Column
    private int cvv;
    @Column
    private int expiration_month;
    @Column
    private int expiration_year;
    @Column
    private int status;
    @Column
    private int NIP;



















}
