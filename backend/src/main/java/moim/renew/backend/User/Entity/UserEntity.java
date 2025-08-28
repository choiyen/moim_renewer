package moim.renew.backend.User.Entity;

import lombok.*;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.config.Enum.GenderEnum;
import moim.renew.backend.config.Enum.ProviderEnum;
import moim.renew.backend.config.Enum.UserTypeEnum;
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
        System.out.println("userId: " + this.userId);
        System.out.println("nickname: " + this.nickname);
        System.out.println("review: " + this.review);
        System.out.println("password: " + this.password);
        System.out.println("Intro: " + this.Intro);
        System.out.println("provider: " + this.provider);
        System.out.println("profileImg: " + this.profileImg);
        System.out.println("gender: " + this.gender);
        System.out.println("address: " + this.address);
        System.out.println("addressDetail: " + this.addressDetail);
        System.out.println("birthDay: " + this.birthDay);
        System.out.println("interests: " + this.interests);
        System.out.println("type: " + this.type);
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
                .password(passwordEncoder.encode(this.password))
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
