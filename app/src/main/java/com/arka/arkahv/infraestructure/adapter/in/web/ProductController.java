package com.arka.arkahv.infraestructure.adapter.in.web;


import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.in.ProductUseCase;
import com.arka.arkahv.domain.port.in.UploadProductImageUseCase;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.ProductDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.ProductWebMapper;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

import java.util.List;

@RestController
@RequestMapping("arka/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductWebMapper productWebMapper;
    private final UploadProductImageUseCase uploadProductImageUseCase;

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
    /**
     * Endpoint para subir una imagen de producto.
     * Este endpoint podría ser parte de la creación o actualización de un producto.
     * Para simplificar, aquí solo se muestra la lógica de subida de imagen.
     *
     * @param imagen El archivo MultipartFile de la imagen.
     * @return Una ResponseEntity con la URL de la imagen subida o un mensaje de error.
     */
    @PostMapping("/products/image-upload") // Nuevo endpoint específico para subir imágenes
    public ResponseEntity<String> uploadProductImage(@RequestParam("image") MultipartFile imagen) {
        if (imagen == null || imagen.isEmpty()) {
            return new ResponseEntity<>("Se requiere una imagen para subir.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Llama al caso de uso para subir la imagen
            String imageUrl = uploadProductImageUseCase.uploadProductImage(imagen);

            // En un escenario real, aquí integrarías esta URL con tu lógica de producto,
            // por ejemplo, guardándola en la base de datos junto con los detalles del producto.
            // Ejemplo: manageProductUseCase.updateProductImageUrl(productId, imageUrl);

            return new ResponseEntity<>("Imagen subida exitosamente. URL: " + imageUrl, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Captura errores de validación (ej. imagen vacía)
            return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            // Captura errores durante el proceso de subida (ej. S3, I/O)
            System.err.println("Error al subir imagen: " + e.getMessage());
            return new ResponseEntity<>("Error al subir la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reporte")
    public ResponseEntity<byte[]> generarPdfDesdeController() {
        try {
            /*
            // Simulamos una lista de productos (puedes traerla de tu base)
            List<Producto> productos = List.of(
                    new Producto("Producto A", 100.0, 2),
                    new Producto("Producto B", 50.0, 5),
                    new Producto("Producto C", 200.0, 1)
            );
            */
            List<Product> products = productUseCase.getAllProductsStock();
            List <ProductDTO> productos = products.stream()
                    .map(productWebMapper::productToProductDTO)
                    .collect(Collectors.toList());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título
            document.add(new Paragraph("REPORTE DE PRODUCTOS").setBold().setFontSize(16).setMarginBottom(20));

            // Tabla con 3 columnas
            Table table = new Table(3);
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Precio");
            table.addHeaderCell("Cantidad");

            for (ProductDTO p : productos) {
                table.addCell(p.getDescription());
                table.addCell(String.valueOf(p.getPrice()));
                table.addCell(String.valueOf(p.getStock()));
            }

            document.add(table);
            document.close();

            byte[] pdfBytes = out.toByteArray();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_productos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("Error al generar el PDF: " + e.getMessage()).getBytes());
        }


    }


}
