package moim.renew.backend.domain.Consult.ConsultCategory.Entity;

import lombok.*;
import moim.renew.backend.domain.Consult.ConsultCategory.DTO.ConsultCategoryDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ConsultCategoryEntity
{
    private Integer consultCategoryId;
    private String consultType;


    public ConsultCategoryDTO ConvertTo()
    {
        return ConsultCategoryDTO.builder()
                .consultCategoryId(this.consultCategoryId)
                .consultType(this.consultType)
                .build();
    }
    public static ConsultCategoryEntity of(Integer id, String name) {
        return new ConsultCategoryEntity(id, name);
    }
}
