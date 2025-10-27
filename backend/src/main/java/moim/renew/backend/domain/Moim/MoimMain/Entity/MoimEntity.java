package moim.renew.backend.domain.Moim.MoimMain.Entity;


import lombok.*;
import moim.renew.backend.domain.Moim.MoimMain.DTO.MoimDTO;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class MoimEntity
{
    private String moimId;
    private String title;
    private Boolean isOnline;
    private Integer maxPeople;
    private Date expirationDate;
    private Date evenDate;
    private String location;
    private String representImg;
    private String organizer;
    private String description;
    private String[] tag;
    private String categoryDetailId;

    public MoimDTO convertTo()
    {
        return MoimDTO.builder()
                .moimId(this.moimId)
                .title(this.title)
                .isOnline(this.isOnline)
                .maxPeople(this.maxPeople)
                .organizer(this.organizer)
                .evenDate(this.evenDate)
                .expirationDate(this.expirationDate)
                .location(this.location)
                .representImg(this.representImg)
                .description(this.description)
                .tag(this.tag)
                .categoryDetailId(this.categoryDetailId)
                .build();
    }
    public MoimEntity convertToReNew(MoimEntity oldMoim)
    {
        return MoimEntity.builder()
                .moimId(oldMoim.getMoimId())
                .title(this.title)
                .isOnline(this.isOnline)
                .maxPeople(this.maxPeople)
                .organizer(this.organizer)
                .evenDate(this.evenDate)
                .expirationDate(this.expirationDate)
                .location(this.location)
                .representImg(this.representImg)
                .description(this.description)
                .tag(this.tag)
                .categoryDetailId(this.categoryDetailId)
                .build();
    }
}
