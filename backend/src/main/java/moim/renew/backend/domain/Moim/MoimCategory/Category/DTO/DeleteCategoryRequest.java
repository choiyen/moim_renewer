package moim.renew.backend.domain.Moim.MoimCategory.Category.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DeleteCategoryRequest {
    @JsonProperty("MoimCategoryId")
    private Integer MoimCategoryId;
}
