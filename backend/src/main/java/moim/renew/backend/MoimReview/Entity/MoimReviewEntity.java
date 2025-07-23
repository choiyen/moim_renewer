package moim.renew.backend.MoimReview.Entity;


import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimReviewEntity
{
    private Integer moimId;
    private String reviewerNickname;
    private Float score;
    private String comment;
}
