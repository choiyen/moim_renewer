package moim.renew.backend.MoimReview.DTO;

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
    private String reviewerNickname;
    private Float score;
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
