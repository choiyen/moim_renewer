package moim.renew.backend.domain.Consult.ConsultMain.DTO;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DeleteConsultDTO
{
    private String moimConsultId;
    private String nickname;
}
