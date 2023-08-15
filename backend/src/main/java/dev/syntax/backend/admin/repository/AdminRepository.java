package dev.syntax.backend.admin.repository;

import dev.syntax.backend.admin.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//관리자 테이블 관련 DB 설정
public interface AdminRepository extends CrudRepository<Admin, Long> {

}
