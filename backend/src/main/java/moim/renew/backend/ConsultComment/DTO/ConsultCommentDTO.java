package moim.renew.backend.ConsultComment.DTO;

import lombok.*;
import moim.renew.backend.ConsultComment.Entity.ConsultCommentEntity;

import java.security.SecureRandom;

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


    public String generateRandomId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public ConsultCommentEntity convertTo()
    {
        // categoryDetailId가 null이면 랜덤 생성
        if (this.moimConsultCommentId == null || this.moimConsultCommentId.isEmpty()) {
            this.moimConsultCommentId = generateRandomId();
        }

        return ConsultCommentEntity.builder()
                .moimConsultCommentId(this.moimConsultCommentId)
                .moimConsultId(this.moimConsultId)
                .nickname(this.nickname)
                .password(this.password)
                .comments(this.comments)
                .build();
    }
}
