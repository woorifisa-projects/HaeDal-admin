package dev.syntax.backend.admin.controller;

import dev.syntax.backend.admin.model.Admin;
import dev.syntax.backend.admin.service.AdminService;
import dev.syntax.backend.user.dto.response.AdminResponse;
import dev.syntax.backend.user.model.User;
import dev.syntax.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 관리자 페이지의 메인 페이지 = 상품 관리 페이지
@CrossOrigin(origins = {"http://localhost:3000, http://3.35.119.36"})
// 경로 변경
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    //관리자 페이지 접속시 password 확인
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestParam("password") Long enteredPassword) {
        Admin admin = adminService.findById(1L); // id가 1인 관리자 정보 가져옴
        //입력된 비밀번호가 DB와 같을 시 유저 리스트 페이지로 이동
        if (admin != null && admin.getPassword().equals(enteredPassword)) {
            return  ResponseEntity.ok("관리자 확인 되었습니다.");

            //비밀번호와 다를 시 이동X
        } else {
            return  ResponseEntity.badRequest().body("관리자 정보를 다시 확인해주세요.");
        }
    }
}
