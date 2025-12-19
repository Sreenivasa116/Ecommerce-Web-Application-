package com.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "ecom_cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "cart_item_quantity")
    private Integer quantity;

    @Column(name="product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;


    // relations
    // Many to one cart items to cart
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;
}
