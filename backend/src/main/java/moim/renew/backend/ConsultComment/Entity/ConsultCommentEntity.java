package moim.renew.backend.ConsultComment.Entity;

import lombok.*;
import moim.renew.backend.ConsultComment.DTO.ConsultCommentDTO;

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


    public ConsultCommentDTO convertTo()
    {
        return ConsultCommentDTO.builder()
                .moimConsultCommentId(this.moimConsultCommentId)
                .moimConsultId(this.moimConsultId)
                .nickname(this.nickname)
                .password(this.password)
                .comments(this.comments)
                .build();
    }
}
