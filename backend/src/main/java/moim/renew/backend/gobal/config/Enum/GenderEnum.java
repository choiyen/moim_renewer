package moim.renew.backend.gobal.config.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GenderEnum
{
    MALE,FEMALE;

    @JsonCreator
    public static GenderEnum from(String value) {
        try {
            return GenderEnum.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("provider는 지원하지 않는 값입니다.");
        }
    }
}

