package moim.renew.backend.MoimCategory.Category.DTO;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MoimCateGoryDTO
{
    private Integer categoryId;
    private String categorisation;
}
