package moim.renew.backend.MoimDetail.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimDetailDTO
{
    private String moimId;

    @NotBlank(message = "모임과 관련된 내용은 반드시 포함되어야 합니다.")
    private String content;
    private Integer minPeople;
    private Double Pay;
    private Boolean approval;
}
