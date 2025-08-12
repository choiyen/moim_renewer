package moim.renew.backend.User.DTO;

import lombok.*;
import moim.renew.backend.User.Entity.UserEntity;
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
    private String user_id;
    private String password;
    private String nickname;
    private String Intro;
    private String provider;
    private Float review = 3.0f; // 기본값 설정

    public UserEntity convertTo()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return UserEntity.builder()
                .user_id(this.user_id)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .review(this.review)
                .Intro(this.Intro)
                .provider(this.provider)
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
