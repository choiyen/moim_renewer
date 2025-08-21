package moim.renew.backend.config.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProviderEnum
{
    NAVER, GOOGLE, KAKAO, DEFAULT;


    @JsonCreator
    public static ProviderEnum from(String value) {
        try {
            return ProviderEnum.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("provider는 지원하지 않는 값입니다.");
        }
    }
}
