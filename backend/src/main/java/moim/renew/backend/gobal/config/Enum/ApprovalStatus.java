package moim.renew.backend.gobal.config.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ApprovalStatus {
        PENDED,     // 대기 중 (기본값)
        APPROVED,   // 승인됨
        REJECTED,   // 거절됨
        CANCELLED;   // 취소됨 (사용자 또는 모임 취소)
    @JsonCreator
    public static ApprovalStatus from(String value) {
        try {
            return ApprovalStatus.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 ApprovalStatus는 지원하지 않는 값입니다.");
        }
    }
}
