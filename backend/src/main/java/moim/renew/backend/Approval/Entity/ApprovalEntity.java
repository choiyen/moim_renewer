package moim.renew.backend.Approval.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import moim.renew.backend.Approval.DTO.ApprovalDTO;
import moim.renew.backend.config.Enum.ApprovalStatus;
import org.joda.time.DateTime;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ApprovalEntity
{
    private String moimId;
    private String userNickname;
    private String status;
    private DateTime requestedAt;
    private DateTime approvalAt;

    public ApprovalDTO ConvertTo()
    {
        return ApprovalDTO.builder()
                .moimId(this.moimId)
                .userNickname(this.userNickname)
                .status(ApprovalStatus.valueOf(this.status))
                .approvalAt(this.approvalAt)
                .requestedAt(this.requestedAt)
                .build();
    }
}
