package dev.syntax.backend.admin.dto.response;


import dev.syntax.backend.admin.model.Admin;
import dev.syntax.backend.admin.model.ServicePurpose;
import dev.syntax.backend.admin.model.User;
import dev.syntax.backend.admin.model.UserAgeGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

//고객 response dto
@Getter
@Setter
public class UserResponse {
    //pk키 아이디
    private Long userId;
    private String name;
    private String id;
    private ServicePurpose servicePurpose;
    private UserAgeGroup userAgeGroup;
    private Long asset;

    @Builder
    public UserResponse(Long userId, String name, String id, ServicePurpose servicePurpose, UserAgeGroup userAgeGroup, Long asset) {
        this.userId = userId;
        this.name = name;
        this.id = id;
        this.servicePurpose = servicePurpose;
        this.userAgeGroup = userAgeGroup;
        this.asset = asset;
    }
    public static UserResponse from(User user) {
        final Long userId = user.getUserId();
        final String id = user.getId();
        final String name = user.getName();
        final ServicePurpose servicePurpose = user.getServicePurpose();
        final UserAgeGroup userAgeGroup = user.getUserAgeGroup();
        final Long asset = user.getAsset();

        return UserResponse.builder()
                .userId(userId)
                .id(id)
                .name(name)
                .servicePurpose(servicePurpose)
                .userAgeGroup(userAgeGroup)
                .asset(asset)
                .build();
    }
}
