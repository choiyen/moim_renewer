package moim.renew.backend.UserReview.DTO;

import lombok.*;
import moim.renew.backend.UserReview.Entity.UserReviewEntity;

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

    public UserReviewEntity convertTo()
    {
        return UserReviewEntity.builder()
                .id(this.id)
                .reviewee(this.reviewee)
                .reviewer(this.reviewee)
                .score(this.score)
                .build();
    }
}
