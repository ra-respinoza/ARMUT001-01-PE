package com.furnitureAssembly.productList.mapper;

import com.furnitureAssembly.productList.dto.ProductDTO;
import com.furnitureAssembly.productList.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product producto) {
        return ProductDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .activo(producto.getActivo())
                .build();
    }

    public Product toEntity(ProductDTO productoDTO) {
        return Product.builder()
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .precio(productoDTO.getPrecio())
                .activo(productoDTO.getActivo()) // Por defecto, el producto se crea como activo
                .stock(productoDTO.getStock())
                .build();
    }
}
