package moim.renew.backend.Moim.DTO;

import lombok.*;
import jakarta.validation.constraints.*;
import moim.renew.backend.Moim.Entity.MoimEntity;

import java.util.Date;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class MoimDTO
{

    @NotNull(message = "moimId는 null일 수 없습니다.")
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

    @NotNull(message = "모임의 주최자 설정은 필수입니다.")
    private String organizer;

    @NotNull(message = "모임 예정일은 필수입니다.")
    private Date evenDate;

    @Size(max = 25, message = "장소는 최대 25자까지 입력 가능합니다.")
    private String location;

    @Size(max = 255, message = "이미지 URL은 255자 이하로 입력해주세요.")
    private String representImg;

    @NotBlank(message = "카테고리는 필수입니다.")
    @Size(max = 40, message = "카테고리는 최대 40자까지 입력 가능합니다.")
    private String category;


    public MoimEntity convertTo()
    {
        return MoimEntity.builder()
                .moimId(this.moimId)
                .category(this.category)
                .evenDate(this.evenDate)
                .expirationDate(this.expirationDate)
                .isOnline(this.isOnline)
                .location(this.location)
                .maxPeople(this.maxPeople)
                .title(this.title)
                .organizer(this.organizer)
                .build();
    }
}
