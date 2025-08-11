package moim.renew.backend.MoimDetail.DTO;

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
    private String content;
    private Integer minPeople;
    private Double Pay;
    private List<String> Member;
}
