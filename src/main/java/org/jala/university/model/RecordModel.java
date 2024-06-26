package org.jala.university.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jala.university.dao.EntityDAO;

import java.util.Date;
import java.util.UUID;

@Entity(name = "record")
@Getter
@Setter
@Builder

public class RecordModel implements EntityDAO<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "recordCreditCard")
    private UUID id;
    @Column
    private String operation;
    @Column
    private double current_limit;
    @Column
    private int PIN;
    @Column
    private int status;
    @Column
    private Date date;
    @ManyToOne
    @JoinColumn(name = "creditCardId")
    private CreditCardModel creditCard;
}

