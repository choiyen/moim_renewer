package moim.renew.backend.UserReview.DTO;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UserReviewDTO
{
    private Integer id;
    private String reviewer;
    private String reviewee;
    private Float score = 10.0f; // 기본값 설정
}
