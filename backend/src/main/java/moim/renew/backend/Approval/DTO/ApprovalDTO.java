package moim.renew.backend.Approval.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import moim.renew.backend.Approval.Entity.ApprovalEntity;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class ApprovalDTO {

    @NotBlank(message = "moimId는 필수 입력값입니다.")
    @Size(max = 20, message = "moimId는 최대 20자 이하여야 합니다.")
    private String moimId;

    @NotNull(message = "joinmember 배열은 null일 수 없습니다.")
    @Size(min = 1, max = 50, message = "joinmember 배열은 1개 이상 50개 이하여야 합니다.")
    private String[] joinmember;

    @NotNull(message = "approval 배열은 null일 수 없습니다.")
    @Size(max = 50, message = "approval 배열은 최대 50개 이하여야 합니다.")
    private String[] approval;


    public ApprovalEntity ConvertTo()
    {
        return ApprovalEntity.builder()
                .moimId(this.moimId)
                .joinmember(this.joinmember)
                .approval(this.approval)
                .build();
    }
}
