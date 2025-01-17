package com.application.Ecommerce.service.product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.application.Ecommerce.dto.ImageDto;
import com.application.Ecommerce.dto.ProductDto;
import com.application.Ecommerce.exceptions.ResourceNotFoundException;
import com.application.Ecommerce.model.Category;
import com.application.Ecommerce.model.Image;
import com.application.Ecommerce.model.Product;
import com.application.Ecommerce.repository.CategoryRepository;
import com.application.Ecommerce.repository.ImageRepository;
import com.application.Ecommerce.repository.ProductRepository;
import com.application.Ecommerce.request.AddProductRequest;
import com.application.Ecommerce.request.ProductUpdateRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        // check if the category is found in the DB
        // If Yes, set it as the new product category
        // If No, then save it as a new category
        // The set as the new product category.
    	// Here optional because may be the category is available or may not be available
    	// request.getCategory().getName()) == > checking whether the category is there in databse or not
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))//checking here whether category is there or not
               // if the category is not there then creating a new category
        		.orElseGet(() -> {
        			// creating the new caategory
                    Category newCategory = new Category(request.getCategory().getName());
                    // Saving to repo
                    return categoryRepository.save(newCategory);
                });
        // setting here the category for which we want to add a product my be a new category or existing one
        request.setCategory(category);
        //After creating / setting the existing Category create a product for the same Category
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
        						//if present delete the product or else throw the exception
                .ifPresentOrElse(productRepository::delete,
                        () -> {throw new ResourceNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
        		//If the product was found (i.e., the Optional is present), the lambda function is executed
                .map(existingProduct -> updateExistingProduct(existingProduct,request))
                //Equivalent to """.map(product -> productRepository.save(product))"""
                .map(productRepository :: save)
                //executes only if the product is not found
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return  existingProduct;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
      return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
