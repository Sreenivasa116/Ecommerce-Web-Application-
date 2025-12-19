package com.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ecom_order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price",precision = 19,scale = 2,nullable = false)
    private BigDecimal price;
    @Column(name = "total",precision = 19,scale = 2,nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}

