package com.furnitureAssembly.productList.service;

import java.util.List;

import com.furnitureAssembly.productList.dto.ProductDTO;

public interface ProductService {

    List<ProductDTO> listarProductos();
    void  crearProducto(ProductDTO productoDTO);
    void actualizarProducto(Long id, ProductDTO productoDTO);
    void eliminarProducto(Long id);
    List<ProductDTO> listarProductosTodos();
}
