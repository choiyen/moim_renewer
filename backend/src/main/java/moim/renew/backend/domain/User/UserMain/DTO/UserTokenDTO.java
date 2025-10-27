package moim.renew.backend.domain.User.UserMain.DTO;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UserTokenDTO
{
    private UserDTO userDTO;
    private String token;
}
