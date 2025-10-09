package moim.renew.backend.ConsultComment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class DeletedCommentDTO
{
    @JsonProperty("MoimConsultCommentId")
    private String MoimConsultCommentId;
    @JsonProperty("Password")
    private String Password;
}
