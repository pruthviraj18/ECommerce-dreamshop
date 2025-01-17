package com.application.Ecommerce.dto;

import lombok.Data;

import java.util.List;

import com.application.Ecommerce.model.Cart;
import com.application.Ecommerce.model.Order;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
