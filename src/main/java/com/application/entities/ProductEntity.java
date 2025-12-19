package com.application.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "ecom_products",
        indexes = {
                @Index(name = "idx_product_name", columnList = "product_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"orderList", "cartItemList"})
@EqualsAndHashCode(exclude = {"orderList", "cartItemList"})
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "product_description", length = 500)
    private String description;

    @Column(name = "price", precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderList;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CartItemEntity> cartItemList;

  
}

