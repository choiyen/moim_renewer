package moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity;

import lombok.*;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.DTO.MoimCategoryDetailDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimCategoryDetailEntity {

    private String categoryDetailId;
    private Integer categoryId;
    private String categorisationDetail;


    public MoimCategoryDetailDTO ConvertTo()
    {
        return MoimCategoryDetailDTO.builder()
                .categoryId(this.categoryId)
                .categoryDetailId(this.categoryDetailId)
                .categorisationDetail(this.categorisationDetail)
                .build();
    }
}