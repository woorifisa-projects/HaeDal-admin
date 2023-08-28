package dev.syntax.backend.product.service;

import dev.syntax.backend.product.dto.response.ProductResponse;
import dev.syntax.backend.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService extends CrudService<Product, Long> {

    Product save(Product newProduct);

    void delete(Product findProduct);
}
