package dev.syntax.backend.admin.controller;

import dev.syntax.backend.admin.dto.response.AdminResponse;
import dev.syntax.backend.admin.dto.response.UserResponse;
import dev.syntax.backend.admin.model.User;
import dev.syntax.backend.admin.service.AdminService;
import dev.syntax.backend.admin.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//관리자 페이지의 모든 기능을 관장하는 controller
// user, product, admin 모든 테이블을 이곳에서 활용합니다.
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class AdminController {

    //service를 활용하기 위한 의존성 주입
    private final AdminService adminService;
    private final UserService userService;
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    //관리자 페이지 접속시 password 확인
    @PostMapping("/login")
    public String adminLogin(@RequestParam("password") Long enteredPassword, Model model) {
        AdminResponse admin = adminService.findById(1L); // id가 1인 관리자 정보 가져옴

        //입력된 비밀번호가 DB와 같을 시 유저 리스트 페이지로 이동
        if (admin != null && admin.getPassword().equals(enteredPassword)) {
            model.addAttribute("admin", admin);
            return "/user_list";

            //비밀번호와 다를 시 이동X
        } else {
            model.addAttribute("error", "Invalid password");
            return "/login";
        }
    }

    //고객 리스트 전체 조회
    @GetMapping("/user_list")
    public List<User> userList(){
        return userService.findAll();
    }

    //특정 고객 정보 수정 화면
    @GetMapping("/{user_id}/edit")
    public User showEdit(@PathVariable("user_id") Long userId) {
        User user = userService.findById(userId);
        System.out.println(user);
        return user;
    }

    // 수정 제출 버튼시
    @PostMapping("/{user_id}/edit")
    public User submitEdit(@PathVariable("user_id") Long userId, @RequestBody User editUser) {
        editUser.setUserId(userId);
        User updatedUser = userService.saveUser(editUser);
        return updatedUser;
    }


    //특정 고객 정보 삭제
    //TODO : 동작하지 않는 이유 찾기
    @DeleteMapping("/{user_id}/delete")
    public void deleteUser(@PathVariable("user_id") Long userId){
        User user = userService.findById(userId);
        userService.delete(user);
        System.out.println("삭제되었습니다");
    }


    //상품 리스트 전체 조회
    //TODO : 상품 관련 DB 설계가 모두 끝난 후 추가한다.
    @GetMapping("/product_list")
    public String ProductList(){
        return null;
    }

    //상품 정보 수정

    //상품 삭제

    //상품 추가
}
