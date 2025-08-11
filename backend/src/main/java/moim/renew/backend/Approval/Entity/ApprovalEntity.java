package moim.renew.backend.Approval.Entity;

import lombok.*;
import moim.renew.backend.Approval.DTO.ApprovalDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class ApprovalEntity
{
    private String moimId;
    private String[] joinmember;
    private String[] approval;

    public ApprovalDTO ConvertTo()
    {
        return ApprovalDTO.builder()
                .moimId(this.moimId)
                .joinmember(this.joinmember)
                .approval(this.approval)
                .build();
    }
}
