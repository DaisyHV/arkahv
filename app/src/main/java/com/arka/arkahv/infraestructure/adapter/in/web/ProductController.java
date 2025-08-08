package com.arka.arkahv.infraestructure.adapter.in.web;


import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.in.ProductUseCase;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.ProductDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.ProductWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("arka/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductWebMapper productWebMapper;

    //Muestra todos los productos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<Product> products = productUseCase.getAllProducts();
        List <ProductDTO> productDTOS = products.stream()
                .map(productWebMapper::productToProductDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDTOS,HttpStatus.OK);
    }
    //Muestra un producto en base a su id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductId(@PathVariable int id){
        Product product = productUseCase.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productWebMapper.productToProductDTO(product),HttpStatus.OK);

    }
    @PostMapping()
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO){
        try {
            Product product = productWebMapper.productDTOToProduct(productDTO);
            product = productUseCase.createProduct(product);
            return new ResponseEntity<>(productWebMapper.productToProductDTO(product), HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTO productDTO){
        Product updateProduct = productWebMapper.productDTOToProduct(productDTO);
        updateProduct = productUseCase.updateProduct(updateProduct);
         if(updateProduct == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productWebMapper.productToProductDTO(updateProduct),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        if(productUseCase.getProductById(id) != null){
            productUseCase.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }





}
