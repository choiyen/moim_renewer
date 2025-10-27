package moim.renew.backend.domain.Consult.ConsultMain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import moim.renew.backend.domain.Consult.ConsultMain.Entity.ConsultEntity;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ConsultDTO {

    private String moimConsultId;

    @JsonProperty("Nickname")
    private String nickname;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("consultCategoryId")
    private Integer consultCategoryId;
    @JsonProperty("consultComment")
    private String consultComment;

    private LocalDateTime createDate;
    private Integer viewcount;

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
                .title(this.title)
                .nickname(this.nickname)
                .createDate(this.createDate)
                .viewcount(this.viewcount)
                .build();
    }
}