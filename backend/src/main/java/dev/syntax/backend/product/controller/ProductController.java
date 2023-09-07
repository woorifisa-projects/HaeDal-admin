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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 관리자 페이지의 메인 페이지 = 상품 관리 페이지

//@CrossOrigin(origins = "http://localhost:3000")

@CrossOrigin(origins = {"http://localhost:3000, http://3.35.119.36"})
//@CrossOrigin(origins = "http://localhost:3000")

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
    @GetMapping("/{productId}/edit")
    public ProductResponse showEdit(@PathVariable("productId") Long productId) {
        Product product = productService.findById(productId);
        return ProductResponse.from(product);
    }

    // 수정 제출 버튼 클릭시
    @PostMapping("/{productId}/edit/save")
    public ProductResponse submitEdit(@PathVariable("productId") Long productId, @RequestBody Product editProduct) {

        editProduct.setProductId(productId);
        editProduct.setProductStatus(true);
        System.out.println("editProduct의 값은? " + editProduct);
        System.out.println("editProduct의 isDeposit 값은? " +  editProduct.isDeposit());

        Product updatedProduct = productService.save(editProduct);
        System.out.println("수정되었습니다.");
        System.out.println(updatedProduct.getUserAgeGroup());

        return ProductResponse.from(updatedProduct);
    }

    // 상품 삭제
    // status == false -> 프론트에서 보이지 않도록
    @PostMapping("/{productId}/delete")
    public ProductResponse deleteProduct(@PathVariable("productId") Long productId) {
        Product findProduct = productService.findById(productId);
        if (findProduct.isProductStatus() == true) {
            if (findProduct != null) {
                findProduct.updateProductStatus(false); // 상품의 productStatus를 0(false)으로 업데이트
                Product updatedStatusProduct = productService.save(findProduct); // 업데이트된 정보 저장
                return ProductResponse.from(updatedStatusProduct);
            }
        }
        return ProductResponse.from(findProduct);
    }


    // 상품 재등록
    // status == false -> 프론트에서 보이지 않도록
    @PostMapping("/{productId}/return")
    public ProductResponse returnProduct(@PathVariable("productId") Long productId) {
        Product findProduct = productService.findById(productId);
        if (findProduct.isProductStatus() == false) {
            if (findProduct != null) {
                findProduct.updateProductStatus(true); // 상품의 productStatus를 1(true)으로 업데이트
                Product updatedStatusProduct = productService.save(findProduct); // 업데이트된 정보 저장
                return ProductResponse.from(updatedStatusProduct);
            }
        }
        return ProductResponse.from(findProduct);
    }

    //상품 추가
    @PostMapping("/add")
    public ProductResponse addProduct(@RequestBody Product enterProduct ) {
        enterProduct.updateProductStatus(true);
        Product savedProduct = productService.save(enterProduct);
        return ProductResponse.from(savedProduct);
    }
}
