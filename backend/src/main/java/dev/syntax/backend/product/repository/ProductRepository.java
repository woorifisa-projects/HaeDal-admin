package dev.syntax.backend.product.repository;

import dev.syntax.backend.product.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
