package com.furnitureAssembly.productList.controller;

import com.furnitureAssembly.productList.dto.ProductDTO;
import com.furnitureAssembly.productList.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> listarProductos() {
        return productService.listarProductos();
    }

    @GetMapping("/all")
    public List<ProductDTO> listarProductosTodos() {
        return productService.listarProductosTodos();
    }

    @PostMapping
    public ResponseEntity<Void> crearProducto(@RequestBody ProductDTO productoDTO) {
        productService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Long id, @RequestBody ProductDTO productoDTO) {
        productService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
