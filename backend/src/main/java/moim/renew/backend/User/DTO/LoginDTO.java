package moim.renew.backend.User.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class LoginDTO
{
    @NotBlank(message = "이메일 데이터가 비어있습니다.")
    @Email(message = "이메일 양식에 맞게 적용되어 있지 않습니다.")
    @JsonProperty("userId")
    private String userId; //이메일

    @NotBlank(message = "패스워드 데이터는 비어있을 수 없습니다.")
    @Size(min = 5, message = "Password는 최소 다섯자리 이상 입력해야 함.")
    @Pattern(
            regexp = "^[a-zA-Z0-9가-힣@!#%]+$",
            message = "Password는 한글, 영어, 숫자, 공백, 일부 특수문자(@, !, #, %)만 입력 가능합니다."
    )
    private String password; //패스워드
}
