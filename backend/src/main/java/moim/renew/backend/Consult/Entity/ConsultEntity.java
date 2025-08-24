package moim.renew.backend.Consult.Entity;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ConsultEntity {

    private String moimConsultId;
    private String nickname;
    private String consultCategoryId;
    private String consultComment;
}
