package moim.renew.backend.domain.Moim.MoimApproval.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import moim.renew.backend.domain.Moim.MoimApproval.Entity.ApprovalEntity;
import moim.renew.backend.gobal.config.Enum.ApprovalStatus;

import java.time.LocalDateTime;

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

    @NotBlank(message = "userId는 필수 입력값입니다.")
    @Size(max = 20, message = "userId는 최대 20자 이하여야 합니다.")
    private String userNickname;

    private ApprovalStatus status;

    @NotNull(message = "requestedAt는 필수 입력값입니다.")
    private LocalDateTime requestedAt;

    @NotNull(message = "approvalAt는 필수 입력값입니다.")
    private LocalDateTime approvalAt;

    public ApprovalEntity ConvertTo()
    {
        return ApprovalEntity.builder()
                .moimId(this.moimId)
                .userNickname(this.userNickname)
                .status(String.valueOf(this.status))
                .approvalAt(this.approvalAt)
                .requestedAt(this.requestedAt)
                .build();
    }
}
