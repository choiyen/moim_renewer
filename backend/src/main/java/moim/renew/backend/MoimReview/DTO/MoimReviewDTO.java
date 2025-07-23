package moim.renew.backend.MoimReview.DTO;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimReviewDTO
{
    private Integer moimId;
    private String reviewerNickname;
    private Float score;
    private String comment;
}
