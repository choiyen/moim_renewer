package moim.renew.backend.domain.User.UserReview.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import moim.renew.backend.domain.User.UserReview.Entity.UserReviewEntity;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UserReviewDTO
{
    private Integer id;

    @NotBlank(message = "리뷰를 주는 사람 데이터는 없으면 곤란합니다.")
    private String reviewer;
    @NotNull(message = "평가를 받는 사람 데이터는 없으면 곤란합니다.")
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
