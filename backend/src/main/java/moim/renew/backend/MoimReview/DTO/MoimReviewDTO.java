package moim.renew.backend.MoimReview.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import moim.renew.backend.MoimReview.Entity.MoimReviewEntity;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimReviewDTO
{
    private String moimId;

    @NotNull(message = "리뷰를 쓴 사람의 닉네임은 포함되어야 합니다.")
    private String reviewerNickname;

    private Float score = 5.0f;
    private String comment;

    public MoimReviewEntity ConvertTo()
    {
        return MoimReviewEntity.builder()
                .moimId(this.moimId)
                .comment(this.comment)
                .reviewerNickname(this.reviewerNickname)
                .score(this.score)
                .build();
    }
}
