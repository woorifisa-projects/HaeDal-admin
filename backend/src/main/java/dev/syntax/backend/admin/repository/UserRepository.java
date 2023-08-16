package dev.syntax.backend.admin.repository;

import dev.syntax.backend.admin.dto.response.UserResponse;
import dev.syntax.backend.admin.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//고객 테이블 관련 DB 설정
public interface UserRepository extends CrudRepository<User,Long> {

}
