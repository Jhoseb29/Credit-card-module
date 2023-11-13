package org.jala.university.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jala.university.dao.EntityDAO;

import java.util.Date;
import java.util.UUID;
@Entity(name = "form")
@Getter
@Setter
@Builder
public class FormModel implements EntityDAO<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_form",nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false,name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false)
    private double income;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date birthdate;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, columnDefinition = "DATE")
    private Date aplicationDate;


}
