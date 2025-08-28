package moim.renew.backend.MoimCategory.CategoryDetail.DTO;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MoimCategoryDetailDTO {

    private String categoryDetailId;
    private Integer categoryId;
    private String categorisationDetail;
}
