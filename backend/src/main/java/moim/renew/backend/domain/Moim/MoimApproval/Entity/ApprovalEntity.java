package moim.renew.backend.domain.Moim.MoimApproval.Entity;

import lombok.*;
import moim.renew.backend.domain.Moim.MoimApproval.DTO.ApprovalDTO;
import moim.renew.backend.gobal.config.Enum.ApprovalStatus;

import java.time.LocalDateTime;

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
    private LocalDateTime requestedAt;
    private LocalDateTime approvalAt;

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
