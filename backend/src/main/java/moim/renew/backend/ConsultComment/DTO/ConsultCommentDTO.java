package moim.renew.backend.ConsultComment.DTO;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ConsultCommentDTO
{
    private String moimConsultCommentId;
    private String moimConsultId;
    private String nickname;
    private String password;
    private String comments;
}
