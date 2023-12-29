package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.Category;
import com.kadirugurlu.advertproject.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements BaseService<Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> read(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(Category entity, Long id) {
        Category category = read(id).orElse(null);

        if(category != null) {
            category.setName(entity.getName());
            return categoryRepository.save(category);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(categoryRepository::delete);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
