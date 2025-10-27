package moim.renew.backend.domain.Moim.MoimReview.Entity;


import lombok.*;
import moim.renew.backend.domain.Moim.MoimReview.DTO.MoimReviewDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimReviewEntity
{
    private String moimId;
    private String reviewer;
    private Float score;
    private String comment;

    public MoimReviewDTO ConvertTo()
    {
        return MoimReviewDTO.builder()
                .moimId(this.moimId)
                .comment(this.comment)
                .reviewer(this.reviewer)
                .score(this.score)
                .build();
    }
    public MoimReviewEntity ConvertToNewScore(Float score)
    {
        return MoimReviewEntity.builder()
                .moimId(this.moimId)
                .comment(this.comment)
                .reviewer(this.reviewer)
                .score(score)
                .build();
    }
    public MoimReviewEntity ConvertToNewcomment(String comment)
    {
        return MoimReviewEntity.builder()
                .moimId(this.moimId)
                .comment(comment)
                .reviewer(this.reviewer)
                .score(this.score)
                .build();
    }
    public MoimReviewEntity ConvertToNew(String comment, Float score)
    {
        return MoimReviewEntity.builder()
                .moimId(this.moimId)
                .comment(comment)
                .reviewer(this.reviewer)
                .score(score)
                .build();
    }
}
