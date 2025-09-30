package moim.renew.backend.Consult.Entity;

import lombok.*;
import moim.renew.backend.Consult.DTO.ConsultDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ConsultEntity {

    private String moimConsultId;
    private String nickname;
    private Integer consultCategoryId;
    private String consultComment;

    public ConsultDTO ConvertTo()
    {

        return ConsultDTO.builder()
                .moimConsultId(this.moimConsultId)
                .consultCategoryId(this.consultCategoryId)
                .consultComment(this.consultComment)
                .build();
    }
}
