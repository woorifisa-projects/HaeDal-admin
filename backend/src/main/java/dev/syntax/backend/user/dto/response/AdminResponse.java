package dev.syntax.backend.user.dto.response;


import dev.syntax.backend.admin.model.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//관리자 dto
@Getter
@Setter
public class AdminResponse {

    private Long id;
    private Long password;

    @Builder
    public AdminResponse(Long id, Long password) {
        this.id = id;
        this.password = password;
    }



    public static AdminResponse adminInfoFrom(Admin admin) {
        return new AdminResponse(admin.getId(), admin.getPassword());
    }
}