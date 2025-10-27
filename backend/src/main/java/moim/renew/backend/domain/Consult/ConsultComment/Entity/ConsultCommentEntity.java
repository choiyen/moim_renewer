package moim.renew.backend.domain.Consult.ConsultComment.Entity;

import lombok.*;
import moim.renew.backend.domain.Consult.ConsultComment.DTO.ConsultCommentDTO;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ConsultCommentEntity
{
    private String moimConsultCommentId;
    private String moimConsultId;
    private String nickname;
    private String password;
    private String comments;
    @EqualsAndHashCode.Exclude
    private LocalDateTime updateDate;
    private String profileImg;

    public ConsultCommentDTO convertTo()
    {
        return ConsultCommentDTO.builder()
                .moimConsultCommentId(this.moimConsultCommentId)
                .moimConsultId(this.moimConsultId)
                .nickname(this.nickname)
                .password(this.password)
                .comments(this.comments)
                .updateDate(this.updateDate)
                .profileImg(this.profileImg)
                .build();
    }
}
