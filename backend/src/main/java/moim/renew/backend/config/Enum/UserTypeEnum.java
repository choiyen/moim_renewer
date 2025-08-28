package moim.renew.backend.config.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserTypeEnum
{
    MANAGER, MEMBER;

    @JsonCreator
    public static UserTypeEnum from(String value) {
        try {
            return UserTypeEnum.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("provider는 지원하지 않는 값입니다.");
        }
    }

}
