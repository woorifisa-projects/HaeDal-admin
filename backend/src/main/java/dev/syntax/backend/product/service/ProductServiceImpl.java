package dev.syntax.backend.product.service;

import dev.syntax.backend.product.model.Product;
import dev.syntax.backend.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public void delete(Product findProduct) {
        productRepository.delete(findProduct);
    }
}
