package moim.renew.backend.MoimCategory.Category.Entity;

import lombok.*;
import moim.renew.backend.ConsultCategory.DTO.ConsultCategoryDTO;
import moim.renew.backend.ConsultCategory.Entity.ConsultCategoryEntity;
import moim.renew.backend.MoimCategory.Category.DTO.MoimCateGoryDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimCateGoryEntity
{
    private Integer categoryId;
    private String categorisation;

    public MoimCateGoryDTO ConvertTo()
    {
        return MoimCateGoryDTO.builder()
                .categoryId(this.categoryId)
                .categorisation(this.categorisation)
                .build();
    }
}
