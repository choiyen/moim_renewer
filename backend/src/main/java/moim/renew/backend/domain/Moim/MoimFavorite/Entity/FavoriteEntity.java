package moim.renew.backend.domain.Moim.MoimFavorite.Entity;


import lombok.*;
import moim.renew.backend.domain.Moim.MoimFavorite.DTO.FavoriteDTO;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class FavoriteEntity
{
    private Integer favoriteId;
    private String moimId;
    private String userId;

    public FavoriteDTO convertTo()
    {
        return FavoriteDTO.builder()
                .moimId(this.moimId)
                .userId(this.userId)
                .favoriteId(this.favoriteId)
                .build();
    }
}
