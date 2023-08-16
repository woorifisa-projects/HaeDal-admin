package dev.syntax.backend.admin.dto.response;


import dev.syntax.backend.admin.model.Admin;
import dev.syntax.backend.admin.model.ServicePurpose;
import dev.syntax.backend.admin.model.User;
import dev.syntax.backend.admin.model.UserAgeGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

//관리자 dto
@Getter
@Setter
public class AdminResponse {

    private Long id;
    private Long password;
    private String log;

    @Builder
    public AdminResponse(Long id, Long password, String log) {
        this.id = id;
        this.password = password;
        this.log = log;
    }



    public static AdminResponse adminInfoFrom(Admin admin) {
        return new AdminResponse(admin.getId(), admin.getPassword(), admin.getLog());
    }
}