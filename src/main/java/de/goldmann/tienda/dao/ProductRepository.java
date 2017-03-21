package de.goldmann.tienda.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.tienda.domain.Product;
import de.goldmann.tienda.domain.ProductCategory;

public interface ProductRepository extends JpaRepository<Product, String> {
    Set<Product> findByCategory(ProductCategory category);
}
