package dev.syntax.backend.admin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 관리자 테이블
@Entity
@Table
@NoArgsConstructor
@Getter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "admin_password")
    Long password;

    @Column
    String log;
}
