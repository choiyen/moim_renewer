package moim.renew.backend.Consult.DTO;

import lombok.*;
import moim.renew.backend.Consult.Entity.ConsultEntity;
import moim.renew.backend.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;

import java.security.SecureRandom;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ConsultDTO {

    private String moimConsultId;
    private String nickname;
    private Integer consultCategoryId;
    private String consultComment;

    public String generateRandomId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
    public ConsultEntity ConvertTo()
    {
        // categoryDetailId가 null이면 랜덤 생성
        if (this.moimConsultId == null || this.moimConsultId.isEmpty()) {
            this.moimConsultId = generateRandomId();
        }

        return ConsultEntity.builder()
                .moimConsultId(this.moimConsultId)
                .consultCategoryId(this.consultCategoryId)
                .consultComment(this.consultComment)
                .build();
    }
}