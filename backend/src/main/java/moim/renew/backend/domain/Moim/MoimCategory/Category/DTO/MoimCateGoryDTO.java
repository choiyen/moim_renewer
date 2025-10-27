package moim.renew.backend.domain.Moim.MoimCategory.Category.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MoimCateGoryDTO
{
    @JsonProperty("MoimCategoryId")
    private Integer categoryId;
    @JsonProperty("categorisation")
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
