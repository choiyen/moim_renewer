package moim.renew.backend.Moim.Entity;


import lombok.*;
import moim.renew.backend.Moim.DTO.MoimDTO;

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
    private String organizer;
    private Date expirationDate;
    private Date evenDate;
    private String location;
    private String representImg;
    private String description;
    private String[] tag;
    private String category;
    private String categoryDetail;

    public MoimDTO convertTo()
    {
        return MoimDTO.builder()
                .moimId(this.moimId)
                .category(this.category)
                .evenDate(this.evenDate)
                .expirationDate(this.expirationDate)
                .isOnline(this.isOnline)
                .location(this.location)
                .maxPeople(this.maxPeople)
                .title(this.title)
                .organizer(this.organizer)
                .categoryDetail(this.categoryDetail)
                .description(this.description)
                .tag(this.tag)
                .build();
    }
    public MoimEntity convertToReNew(MoimEntity oldMoim)
    {
        return MoimEntity.builder()
                .moimId(oldMoim.getMoimId())
                .category(this.category)
                .evenDate(this.evenDate)
                .expirationDate(this.expirationDate)
                .isOnline(this.isOnline)
                .location(this.location)
                .representImg(this.representImg)
                .maxPeople(this.maxPeople)
                .title(this.title)
                .organizer(this.organizer)
                .build();
    }
}
