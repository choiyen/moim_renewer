package moim.renew.backend.domain.User.UserMain.Entity;

import lombok.*;
import moim.renew.backend.domain.User.UserMain.DTO.UserDTO;
import moim.renew.backend.gobal.config.Enum.GenderEnum;
import moim.renew.backend.gobal.config.Enum.ProviderEnum;
import moim.renew.backend.gobal.config.Enum.UserTypeEnum;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class UserEntity
{
    private String userId;
    private String password;
    private String nickname;
    private String Intro;
    private String provider;

    @Builder.Default
    private Float review = 3.0f; // 기본값 설정
    private String profileImg; //프로필 이미지
    private String gender;//성별
    private String birthDay; // 생년월일
    private String type;
    private String address;
    private String addressDetail;
    private String interests;

    public UserDTO convertTo()
    {
        return UserDTO.builder()
                .userId(this.userId)
                .nickname(this.nickname)
                .review(this.review)
                .password(this.password)
                .intro(this.Intro)
                .provider(ProviderEnum.valueOf(this.provider))
                .profileImg(this.profileImg)
                .gender(GenderEnum.valueOf(this.gender))
                .address(this.address)
                .addressDetail(this.addressDetail)
                .birthDay(this.birthDay)
                .interests(this.interests)
                .type(UserTypeEnum.valueOf(this.type))
                .build();
    }
    public UserEntity convertToReNew(UserEntity oldUser)
    {
        return UserEntity.builder()
                .userId(oldUser.userId)
                .password(this.password)
                .nickname(this.nickname)
                .review(oldUser.review)
                .Intro(this.Intro)
                .provider(oldUser.provider)
                .profileImg(this.profileImg)
                .gender(this.gender)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .birthDay(this.birthDay)
                .interests(this.interests)
                .type(this.type)
                .build();
    }
    public UserEntity convertToPassword(PasswordEncoder passwordEncoder)
    {
        return UserEntity.builder()
                .userId(this.userId)
                .password(this.password)
                .nickname(this.nickname)
                .review(this.review)
                .Intro(this.Intro)
                .provider(this.provider)
                .profileImg(this.profileImg)
                .gender(this.gender)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .birthDay(this.birthDay)
                .interests(this.interests)
                .type(this.type)
                .build();
    }
}
