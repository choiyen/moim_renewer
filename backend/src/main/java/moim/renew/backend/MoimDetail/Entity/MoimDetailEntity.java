package moim.renew.backend.MoimDetail.Entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimDetailEntity
{
    private Integer id;
    private String moimId;
    private String content;
    private Integer minPeople;
    private Double Pay;
    private List<String> Member;
}
