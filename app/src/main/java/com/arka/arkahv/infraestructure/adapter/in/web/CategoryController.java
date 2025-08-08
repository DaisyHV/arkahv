package com.arka.arkahv.infraestructure.adapter.in.web;

import com.arka.arkahv.domain.model.Category;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.in.CategoryUseCase;
import com.arka.arkahv.domain.port.in.ProductUseCase;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.CategoryDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.ProductDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.CategoryWebMapper;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.ProductWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("arka/categoiries")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryUseCase categoryUseCase;
    private final CategoryWebMapper categoryWebMapper;

    //Muestra todos los productos
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllcategories(){
        List<CategoryDTO> categories = categoryUseCase.getAllCategories().stream()
                .map(categoryWebMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    //Muestra un producto en base a su id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getProductId(@PathVariable int id){
        Category category = categoryUseCase.getCategoryById(id);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryWebMapper.categoryToCategoryDTO(category), HttpStatus.OK);
    }
}
