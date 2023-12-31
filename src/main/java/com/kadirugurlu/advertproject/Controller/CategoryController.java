package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.DTO.CategoryDTO;
import com.kadirugurlu.advertproject.Entity.Category;
import com.kadirugurlu.advertproject.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO entity){

        Category category = fillCategory(entity);
        return ResponseEntity.ok(categoryService.create(category));
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id){
        if (categoryService.read(id).isPresent()) {
            return ResponseEntity.ok(categoryService.read(id).orElse(null));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody CategoryDTO entity, @PathVariable Long id){
        if(categoryService.read(id).isPresent()) {
            Category category = fillCategory(entity);
            return ResponseEntity.ok(categoryService.update(category, id));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        if(categoryService.read(id).isPresent()) {
            categoryService.delete(id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    private Category fillCategory(CategoryDTO entity) {
        Category category = new Category();
        category.setName(entity.getName());
        return category;
    }
}
