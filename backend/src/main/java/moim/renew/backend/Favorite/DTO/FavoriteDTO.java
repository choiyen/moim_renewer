package moim.renew.backend.Favorite.DTO;

import lombok.*;
import moim.renew.backend.Favorite.Entity.FavoriteEntity;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class FavoriteDTO
{
    private Integer favoriteId;
    private String moimId;
    private String userId;

    public FavoriteEntity convertTo()
    {
        return FavoriteEntity.builder()
                .moimId(this.moimId)
                .userId(this.userId)
                .favoriteId(this.favoriteId)
                .build();
    }
}
