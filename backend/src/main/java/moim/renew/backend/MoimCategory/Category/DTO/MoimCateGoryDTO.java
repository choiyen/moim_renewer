package moim.renew.backend.MoimCategory.Category.DTO;

import lombok.*;
import moim.renew.backend.MoimCategory.Category.Entity.MoimCateGoryEntity;

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

    public MoimCateGoryEntity ConvertTo()
    {
        return MoimCateGoryEntity.builder()
                .categoryId(this.categoryId)
                .categorisation(this.categorisation)
                .build();
    }
    public static MoimCateGoryDTO of(Integer id, String name) {
        return new MoimCateGoryDTO(id, name);
    }
}
