package moim.renew.backend.MoimDetail.Entity;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimDetailEntity
{
    private Integer moimId;
    private String content;
    private Integer minPeople;
}
