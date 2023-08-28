package dev.syntax.backend.user.service;

import dev.syntax.backend.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends CrudService<User, Long> {

    //유저 관련 동작들
    //유저 전체 조회
    List<User> findAll();

    //유저 아이디로 찾기
    User findById(Long id);

    //특정 유저 삭제
    void delete(User user);

    //고객 정보 저장
    User saveUser(User user);
}
