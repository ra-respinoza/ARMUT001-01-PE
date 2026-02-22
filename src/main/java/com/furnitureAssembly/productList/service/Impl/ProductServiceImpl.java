package com.furnitureAssembly.productList.service.Impl;

import com.furnitureAssembly.productList.dto.ProductDTO;
import com.furnitureAssembly.productList.exception.ResourceNotFoundException;
import com.furnitureAssembly.productList.mapper.ProductMapper;
import com.furnitureAssembly.productList.model.Product;
import com.furnitureAssembly.productList.repository.ProductRepository;
import com.furnitureAssembly.productList.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productoRepository;
    private final ProductMapper productoMapper;

    @Override
    public List<ProductDTO> listarProductos() {
        LOGGER.info("Listando productos activos.");
        return productoRepository.findByActivoTrue()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> listarProductosTodos() {
        LOGGER.info("Listando todos los productos.");
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void crearProducto(ProductDTO productoDTO) {
        LOGGER.info("Agregando nuevo producto con datos: {}", productoDTO);
        Product producto = productoMapper.toEntity(productoDTO);
        producto.setActivo(true);
        productoRepository.save(producto);
    }

    @Override
    public void actualizarProducto(Long id, ProductDTO productoDTO) {
        LOGGER.info("Intentando actualizar producto con ID: {} y datos: {}", id, productoDTO);
        Product productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));

        LOGGER.info("Producto encontrado: {}", productoExistente);

        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setStock(productoDTO.getStock());
        productoExistente.setActivo(productoDTO.getActivo());
        productoRepository.save(productoExistente);
        LOGGER.info("Producto con ID: {} actualizado exitosamente.", id);

    }

    @Override
    public void eliminarProducto(Long id) {
        LOGGER.info("Intentando eliminar producto con ID: {}", id);
        Product productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Producto no encontrado con id: {}", id);
                    return new RuntimeException("Producto no encontrado con id: " + id);
                });
        productoRepository.delete(productoExistente);
        LOGGER.info("Producto con ID: {} eliminado exitosamente.", id);

    }
}