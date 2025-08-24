package moim.renew.backend.ConsultCategory.Entity;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ConsultCategoryEntity
{
    private Integer consultCategoryId;
    private String consultType;
}
