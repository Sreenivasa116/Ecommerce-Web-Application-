package com.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cart", "order"})
@EqualsAndHashCode(exclude = {"cart", "order"})
@Table(name= "ecom_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "username",nullable = false)
    private String name;
    @Column(name = "userpassword",nullable = false)
    private String password;
    @Column(name="role",nullable = false)
    private String role;
    @Column(name = "email",nullable = false, unique = true)
    private String email;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(name = "address",nullable = false)
    private String address;


// User Cart One to One
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private CartEntity cart;
//  User Order One to Many
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}
