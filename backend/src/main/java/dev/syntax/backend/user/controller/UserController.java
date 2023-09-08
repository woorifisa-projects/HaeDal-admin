package dev.syntax.backend.user.controller;

import dev.syntax.backend.subscribe.service.SubscribeService;
import dev.syntax.backend.user.dto.response.UserResponse;
import dev.syntax.backend.user.model.User;
import dev.syntax.backend.admin.service.AdminService;
import dev.syntax.backend.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


//@CrossOrigin(origins = "http://localhost:3000")

@CrossOrigin(origins = {"http://localhost:3000", "http://3.35.119.36"})
//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/admin/user")
public class UserController {

    //service를 활용하기 위한 의존성 주입
    private final AdminService adminService;
    private final UserService userService;
    private final SubscribeService subscribeService;
    public UserController(AdminService adminService, UserService userService, SubscribeService subscribeService) {
        this.adminService = adminService;
        this.userService = userService;
        this.subscribeService = subscribeService;
    }

    //고객 리스트 전체 조회
    @GetMapping
    public List<UserResponse> userList(){
        List<User> users = userService.findAll();
        return users.stream().map(UserResponse::from).collect(Collectors.toList());
    }

    //특정 고객 정보 수정 화면
    @GetMapping("/{userId}/edit")
    public UserResponse showEdit(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        return UserResponse.from(user);
    }

    // 수정 제출 버튼시
    @PostMapping("/{userId}/edit")
    public UserResponse submitEdit(@PathVariable("userId") Long userId, @RequestBody User editUser) {
        editUser.setUserId(userId);
        User updatedUser = userService.saveUser(editUser);
        return UserResponse.from(updatedUser);
    }

    //특정 고객 정보 삭제
    // status false로 변경하여 보이지 않도록 수정
    @Transactional
    @PostMapping("/{userId}/delete")
    public UserResponse deleteUser(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        if (user.isUserStatus() == true) {
            if (user != null) {
                user.updateUserStatus(false);
                User updatedUser = userService.saveUser(user);
                subscribeService.deleteByUser(user);
                return UserResponse.from(updatedUser);
            }
        }
        System.out.println("삭제되었습니다");
        return UserResponse.from(user);
    }
    @PostMapping("/{userId}/return")
    public UserResponse returnUser(@PathVariable("userId") Long userId) {
        User user = userService.findById(userId);
        if (user.isUserStatus() == false) {
            if (user != null) {
                user.updateUserStatus(true);
                User updatedUser = userService.saveUser(user);
                return UserResponse.from(updatedUser);
            }
        }
        System.out.println("삭제되었습니다");
        return UserResponse.from(user);
    }
}
