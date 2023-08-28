package dev.syntax.backend.user.controller;

import dev.syntax.backend.product.dto.response.ProductResponse;
import dev.syntax.backend.user.dto.response.UserResponse;
import dev.syntax.backend.user.model.User;
import dev.syntax.backend.admin.service.AdminService;
import dev.syntax.backend.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin/user")
public class UserController {

    //service를 활용하기 위한 의존성 주입
    private final AdminService adminService;
    private final UserService userService;
    public UserController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    //고객 리스트 전체 조회
    @GetMapping("/list")
    public List<UserResponse> userList(){
        List<User> users = userService.findAll();
        return users.stream().map(UserResponse::from).collect(Collectors.toList());
    }

    //특정 고객 정보 수정 화면
    @GetMapping("/{user_id}/edit")
    public UserResponse showEdit(@PathVariable("user_id") Long userId) {
        User user = userService.findById(userId);
        return UserResponse.from(user);
    }

    // 수정 제출 버튼시
    @PostMapping("/{user_id}/edit")
    public UserResponse submitEdit(@PathVariable("user_id") Long userId, @RequestBody User editUser) {
        editUser.setUserId(userId);
        User updatedUser = userService.saveUser(editUser);
        return UserResponse.from(updatedUser);
    }

    //특정 고객 정보 삭제
    @DeleteMapping("/{user_id}/delete")
    public void deleteUser(@PathVariable("user_id") Long userId){
        User user = userService.findById(userId);
        userService.delete(user);
        System.out.println("삭제되었습니다");
    }
}
