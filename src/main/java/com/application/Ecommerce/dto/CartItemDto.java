package com.application.Ecommerce.dto;

import java.math.BigDecimal;

import com.application.Ecommerce.model.Product;

public class CartItemDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;
}
