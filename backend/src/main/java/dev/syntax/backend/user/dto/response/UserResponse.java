package dev.syntax.backend.user.dto.response;


import dev.syntax.backend.user.model.ServicePurpose;
import dev.syntax.backend.user.model.User;
import dev.syntax.backend.user.model.UserAgeGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//고객 response dto
@Getter
@Setter
public class UserResponse {

    private final Long userId;
    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private ServicePurpose servicePurpose;
    private UserAgeGroup userAgeGroup;
    private String accountNumber;
    private Long asset;
    private int authNumber;

    private boolean userStatus;

    @Builder
    public UserResponse(Long userId, String id, String password, String name, String phoneNumber, ServicePurpose servicePurpose, UserAgeGroup userAgeGroup, String accountNumber, Long asset, int authNumber, boolean userStatus) {
        this.userId = userId;
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.servicePurpose = servicePurpose;
        this.userAgeGroup = userAgeGroup;
        this.accountNumber = accountNumber;
        this.asset = asset;
        this.authNumber = authNumber;
        this.userStatus = userStatus;
    }

    public static UserResponse from(User user)  {
        return UserResponse.builder()
                .userId(user.getUserId())
                .id(user.getId())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .servicePurpose(user.getServicePurpose())
                .userAgeGroup(user.getUserAgeGroup())
                .accountNumber(user.getAccountNumber())
                .asset(user.getAsset())
                .authNumber(user.getAuthNumber())
                .userStatus(user.isUserStatus())
                .build();
    }
}
