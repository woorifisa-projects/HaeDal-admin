package dev.syntax.backend.product.controller;

import dev.syntax.backend.admin.model.Admin;
import dev.syntax.backend.product.dto.response.ProductResponse;
import dev.syntax.backend.product.model.Product;
import dev.syntax.backend.product.service.ProductService;
import dev.syntax.backend.user.dto.response.AdminResponse;
import dev.syntax.backend.user.model.User;
import dev.syntax.backend.admin.service.AdminService;
import dev.syntax.backend.user.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// 관리자 페이지의 메인 페이지 = 상품 관리 페이지
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin/product")
public class ProductController {
    
    private final AdminService adminService;
    private final UserService userService;
    private final ProductService productService;
    public ProductController(AdminService adminService, UserService userService, ProductService productService) {
        this.adminService = adminService;
        this.userService = userService;
        this.productService = productService;
    }

    //상품 전체 조회
    @GetMapping
    public List<ProductResponse> getAllproducts( ) {
        List<Product> products  =  productService.findAll();
        List<ProductResponse> productResponse = products.stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
        return productResponse;
    }

    //상품 수정 화면
    @GetMapping("/{product_id}/edit")
    public ProductResponse showEdit(@PathVariable("product_id") Long productId) {
        Product product = productService.findById(productId);
        return ProductResponse.from(product);
    }

    // 수정 제출 버튼시
    @PostMapping("/{product_id}/edit")
    public ProductResponse submitEdit(@PathVariable("product_id") Long productId, @RequestBody Product editProduct) {
        editProduct.setProductId(productId);
        Product updatedProduct = productService.save(editProduct);
        return ProductResponse.from(updatedProduct);
    }

    
    //상품 삭제
    @DeleteMapping("/{id}/delete")
    void deleteProduct(@PathVariable Long id) {
        Product findProduct = productService.findById(id);
        productService.delete(findProduct);
    }

    //상품 추가
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product enterProduct ) {
        Product savedProduct = productService.save(enterProduct);
        return savedProduct;
    }
}
