package com.pavlyk.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email") })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false)
    @Email(message = "Email should be valid")
    private String email;


    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    private String address;
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Bucket bucket;

    @OneToMany(mappedBy = "user")
    Set<Order> orders;

    @Transient
    private String passwordConfirm;
}
