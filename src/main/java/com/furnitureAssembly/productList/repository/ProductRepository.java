package com.furnitureAssembly.productList.repository;

import com.furnitureAssembly.productList.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActivoTrue();

    List<Product> findByActivoFalse();

    List<Product> findByStockLessThanEqual(Integer stock);
}