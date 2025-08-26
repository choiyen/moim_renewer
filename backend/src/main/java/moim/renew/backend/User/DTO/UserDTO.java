package moim.renew.backend.User.DTO;

import jakarta.validation.constraints.*;
import lombok.*;
import moim.renew.backend.User.Entity.UserEntity;
import moim.renew.backend.config.Enum.GenderEnum;
import moim.renew.backend.config.Enum.ProviderEnum;
import moim.renew.backend.config.Enum.UserTypeEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UserDTO
{
    @NotBlank(message = "이메일 데이터가 비어있습니다.")
    @Email(message = "이메일 양식에 맞게 적용되어 있지 않습니다.")
    private String userId; //이메일

    @NotBlank(message = "패스워드 데이터는 비어있을 수 없습니다.")
    @Size(min = 5, message = "Password는 최소 다섯자리 이상 입력해야 함.")
    @Pattern(
            regexp = "^[a-zA-Z0-9가-힣@!#%]+$",
            message = "Password는 한글, 영어, 숫자, 공백, 일부 특수문자(@, !, #, %)만 입력 가능합니다."
    )
    private String password; //패스워드


    @NotBlank(message =  "닉네임 데이터는 비어있을 수 없습니다.")
    @Size(min = 5, max = 20, message = "닉네임은 5자리부터 20자리 이상 입력해야 합니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9가-힣]+$",
            message = "nickname 데이터는 한글, 영어, 숫자 등만 입력할 수 있습니다."
    )
    private String nickname; //닉네임

    @NotBlank(message = "자기소개 데이터는 필수 입력 사항입니다.")
    @Size(min = 20, message = "자기소개 말은 최소 20자리의 글자로 입력하셔야 합니다.")
    private String Intro; //자기소개

    @NotNull(message = "provider는 무조건 포함되어야 하는 데이터입니다.")
    private ProviderEnum provider = ProviderEnum.DEFAULT; //OAuth2 제공자, 기본 값은 노멀
    private Float review = 3.0f; // 기본값 설정

    @NotBlank(message = "프로필 이미지는 무조건 포함되어야 하는 데이터입니다.")
    private String profileImg; //프로필 이미지

    @NotBlank(message = "생년월일 데이터는 반드시 포함되어야 합니다.")
    @Pattern(
            regexp = "^(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
            message = "생년월일은 YYYY-MM-DD 형식이어야 합니다."
    )
    private String birthDay; // 예: 1999-01-01

    @NotNull(message = "성별 데이터는 반드시 포함되어야 합니다.")
    private GenderEnum gender;

    @NotNull(message = "userType은 관리자와 사용자 뿐입니다.")
    private UserTypeEnum type;

    @NotBlank(message = "주소는 반드시 포함되어야 합니다.")
    private String address;
    @NotBlank(message = "세부 주소는 반드시 포함되어야 합니다.")
    private String addressDetail;

    @NotBlank(message = "관심사는 반드시 포함되어야 합니다.")
    private String interests;

    public UserEntity convertTo()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return UserEntity.builder()
                .userId(this.userId)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .review(this.review)
                .Intro(this.Intro)
                .provider(String.valueOf(this.provider))
                .profileImg(this.profileImg)
                .gender(String.valueOf(this.gender))
                .address(this.address)
                .addressDetail(this.addressDetail)
                .birthday(this.birthDay)
                .interests(this.interests)
                .build();
    }
    public UserDTO convertToUrl(String url)
    {
        return UserDTO.builder()
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
                .build();
    }
    public UserTokenDTO convertTo(String Token)
    {
        return UserTokenDTO.builder()
                .userDTO(this)
                .token(Token)
                .build();
    }
}
