package moim.renew.backend.Moim.Entity;


import lombok.*;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimEntity
{
    private Integer moimId;
    private String title;
    private Boolean isOnline;
    private Integer maxPeople;
    private Date expirationDate;
    private Date evenDate;
    private String location;
    private String representImg;
    private String category;
}
