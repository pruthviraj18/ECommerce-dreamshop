package com.application.Ecommerce.service.product;
import java.util.List;

import com.application.Ecommerce.dto.ProductDto;
import com.application.Ecommerce.model.Product;
import com.application.Ecommerce.request.AddProductRequest;
import com.application.Ecommerce.request.ProductUpdateRequest;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    //For the below methods JPA is going to write the 
    //query based on the parameter name and the standard function name
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
