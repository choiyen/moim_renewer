package moim.renew.backend.MoimCategory.CategoryDetail.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class GettingCategoryRequest {
    @JsonProperty("MoimCategoryId")
    private Integer MoimCategoryId;
}
