package dev.syntax.backend.product.service;

import java.util.List;

public interface CrudService<Product,Long>{
    Product findById(Long id);
    List<Product> findAll();
}
