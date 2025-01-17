package com.application.Ecommerce.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.application.Ecommerce.exceptions.AlreadyExistsException;
import com.application.Ecommerce.exceptions.ResourceNotFoundException;
import com.application.Ecommerce.model.Category;
import com.application.Ecommerce.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
        		//we can't add it because it is returning of type Category ,the below one works only if it returns Optional type
        		//.orElseThrow(()->new ResourceNotFoundException("Category with such name not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        //  checks if a category with the same name does not already exist in the database by calling 
    	//  categoryRepository.existsByName(c.getName()).
        //	categoryRepository.existsByName(c.getName()) returns true if a category with the given name already exists in the repository.
        return  Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName()+" already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }) .orElseThrow(()-> new ResourceNotFoundException("Category not found!"));
    }


    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, () -> {
                    throw new ResourceNotFoundException("Category not found!");
                });

    }
}
