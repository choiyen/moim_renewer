package moim.renew.backend.MoimDetail.DTO;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimDetailDTO
{
    private Integer moimId;
    private String content;
    private Integer minPeople;
}
