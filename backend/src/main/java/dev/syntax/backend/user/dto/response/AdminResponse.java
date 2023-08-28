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