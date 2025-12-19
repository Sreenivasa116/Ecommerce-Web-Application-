package com.application.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ecom_cart")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"cartItems"})
@EqualsAndHashCode(exclude = {"cartItems"})
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // USer and cart one to one


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // DB relation cart to cart item One to Many

    @OneToMany(mappedBy="cart",  cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private List<CartItemEntity> cartItems ;
}
