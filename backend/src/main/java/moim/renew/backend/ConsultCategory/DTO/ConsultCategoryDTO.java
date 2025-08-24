package moim.renew.backend.ConsultCategory.DTO;

import lombok.*;

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
}
