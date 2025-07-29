package moim.renew.backend.User.Entity;

import lombok.*;
import moim.renew.backend.Moim.Entity.MoimEntity;
import moim.renew.backend.User.DTO.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class UserEntity
{
    private String user_id;
    private String password;
    private String nickname;
    private String Intro;
    private Float review = 3.0f; // 기본값 설정

    public UserDTO convertTo()
    {
        return UserDTO.builder()
                .user_id(this.user_id)
                .nickname(this.nickname)
                .review(this.review)
                .password(this.password)
                .Intro(this.Intro)
                .build();
    }
    public UserEntity convertToReNew(UserEntity oldUser)
    {
        return UserEntity.builder()
                .user_id(oldUser.user_id)
                .password(this.password)
                .nickname(this.nickname)
                .review(oldUser.review)
                .Intro(this.Intro)
                .build();
    }
    public UserEntity convertToPassword(PasswordEncoder passwordEncoder)
    {
        return UserEntity.builder()
                .user_id(this.user_id)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .review(this.review)
                .Intro(this.Intro)
                .build();
    }
}
