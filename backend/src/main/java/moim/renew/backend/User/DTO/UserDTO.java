package moim.renew.backend.User.DTO;

import lombok.*;

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
    private Float review = 3.0f; // 기본값 설정
}
