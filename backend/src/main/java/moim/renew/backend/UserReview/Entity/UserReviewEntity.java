package moim.renew.backend.UserReview.Entity;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class UserReviewEntity
{
    private Integer id;
    private String reviewer;
    private String reviewee;
    private Float score = 10.0f; // 기본값 설정
}
