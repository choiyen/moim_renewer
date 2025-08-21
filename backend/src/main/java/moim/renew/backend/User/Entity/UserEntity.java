package moim.renew.backend.User.Entity;

import lombok.*;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.config.Enum.GenderEnum;
import moim.renew.backend.config.Enum.ProviderEnum;
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
    private String ProfileImg; //프로필 이미지
    private String Gender;//성별
    private String Birthday; // 생년월일
    private String address;
    private String addressDetail;
    private String Interests;

    public UserDTO convertTo()
    {
        return UserDTO.builder()
                .userId(this.userId)
                .nickname(this.nickname)
                .review(this.review)
                .password(this.password)
                .Intro(this.Intro)
                .provider(ProviderEnum.valueOf(this.provider))
                .ProfileImg(this.ProfileImg)
                .Gender(GenderEnum.valueOf(this.Gender))
                .address(this.address)
                .addressDetail(this.addressDetail)
                .Birthday(this.Birthday)
                .Interests(this.Interests)
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
                .ProfileImg(this.ProfileImg)
                .Gender(this.Gender)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .Birthday(this.Birthday)
                .Interests(this.Interests)
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
                .build();
    }
}
