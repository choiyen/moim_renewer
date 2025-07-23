package moim.renew.backend.User.Entity;

import lombok.*;

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
    private Float review = 3.0f; // 기본값 설정
}
