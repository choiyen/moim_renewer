package moim.renew.backend.domain.Moim.MoimMain.DTO;

import lombok.*;
import jakarta.validation.constraints.*;
import moim.renew.backend.domain.Moim.MoimMain.Entity.MoimEntity;

import java.security.SecureRandom;
import java.util.Date;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimDTO
{

    private String moimId;

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 30, message = "제목은 최대 30자까지 가능합니다.")
    private String title;

    @NotNull(message = "isOnline 값이 필요합니다.")
    private Boolean isOnline;

    @NotNull(message = "최대 인원 수를 입력해주세요.")
    @Max(value = 100, message = "최대 인원은 100명을 초과할 수 없습니다.")
    private Integer maxPeople;

    @NotNull(message = "모집 마감일은 필수입니다.")
    private Date expirationDate;

    @NotNull(message = "모임 예정일은 필수입니다.")
    private Date evenDate;

    @Size(max = 25, message = "장소는 최대 25자까지 입력 가능합니다.")
    private String location;

    @Size(max = 2550, message = "이미지 URL은 255자 이하로 입력해주세요.")
    private String representImg;

    @NotNull(message = "모임의 주최자 설정은 필수입니다.")
    private String organizer;

    @NotBlank(message = "카테고리는 필수입니다.")
    @Size(max = 40, message = "카테고리는 최대 40자까지 입력 가능합니다.")
    private String description;

    @NotNull(message = "tag 배열은 null일 수 없습니다.")
    @Size(max = 50, message = "tag 배열은 최대 50개 이하여야 합니다.")
    private String[] tag;//어떻게 처리할 지 고민중

    @NotBlank(message = "카테고리는 필수입니다.")
    @Size(max = 40, message = "카테고리는 최대 40자까지 입력 가능합니다.")
    private String categoryDetailId;


    public String generateRandomId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public MoimEntity convertTo()
    {
        // categoryDetailId가 null이면 랜덤 생성
        if (this.moimId == null || this.moimId.isEmpty())
        {
            this.moimId = generateRandomId();
        }

        return MoimEntity.builder()
                .moimId(this.moimId)
                .categoryDetailId(this.categoryDetailId)
                .evenDate(this.evenDate)
                .expirationDate(this.expirationDate)
                .isOnline(this.isOnline)
                .location(this.location)
                .maxPeople(this.maxPeople)
                .title(this.title)
                .tag(this.tag)
                .description(this.description)
                .organizer(this.organizer)
                .build();
    }
}
