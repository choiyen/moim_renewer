package moim.renew.backend.Consult.DTO;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ConsultDTO {

    private String moimConsultId;
    private String nickname;
    private String consultCategoryId;
    private String consultComment;

}