package com.application.Ecommerce.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

import com.application.Ecommerce.model.Category;


//@Data is used to generate the automatically the getters and setters and some other functions such as toString()
//While @Entity is used to represent the table in the database
@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
