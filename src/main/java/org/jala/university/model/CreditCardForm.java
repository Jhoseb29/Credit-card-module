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
public class CreditCardForm implements EntityDAO<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private double income;
    @Column
    private Date birthdate;
    @Column
    private String email;
    @Column
    private Date aplicationDate;


}
