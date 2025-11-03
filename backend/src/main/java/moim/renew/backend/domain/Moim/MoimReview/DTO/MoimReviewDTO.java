package moim.renew.backend.domain.Moim.MoimReview.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import moim.renew.backend.domain.Moim.MoimReview.Entity.MoimReviewEntity;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimReviewDTO
{
    private String moimReviewId;

    private String moimId;

    @NotNull(message = "리뷰를 쓴 사람의 닉네임은 포함되어야 합니다.")
    private String reviewer;

    private Float score = 5.0f;
    private String comment;

    public MoimReviewEntity ConvertTo()
    {
        return MoimReviewEntity.builder()
                .moimReviewId(this.moimReviewId)
                .moimId(this.moimId)
                .comment(this.comment)
                .reviewer(this.reviewer)
                .score(this.score)
                .build();
    }
}
