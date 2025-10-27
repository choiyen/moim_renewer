package moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DeleteCategoryDetailRequest {
    @JsonProperty("categoryDetailId")
    private String categoryDetailId;
}
