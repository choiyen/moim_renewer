package moim.renew.backend.ConsultCategory.DTO;

import lombok.*;
import moim.renew.backend.ConsultCategory.Entity.ConsultCategoryEntity;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ConsultCategoryDTO
{
    private Integer consultCategoryId;
    private String consultType;


    public ConsultCategoryEntity ConvertTo()
    {
        return ConsultCategoryEntity.builder()
                .consultCategoryId(this.consultCategoryId)
                .consultType(this.consultType)
                .build();
    }
    public static ConsultCategoryDTO of(Integer id, String name) {
        return new ConsultCategoryDTO(id, name);
    }
}
