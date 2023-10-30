package org.jala.university.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jala.university.dao.EntityDAO;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;
import java.util.UUID;
@Entity
@Getter
@Setter
@Builder
public class    CreditCardForm implements EntityDAO<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idCreditCardForm",nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private double income;
    @Column(nullable = false)
    private Date birthdate;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Date aplicationDate;

}
