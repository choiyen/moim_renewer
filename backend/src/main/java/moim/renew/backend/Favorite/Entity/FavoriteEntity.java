package moim.renew.backend.Favorite.Entity;


import lombok.*;
import moim.renew.backend.Favorite.DTO.FavoriteDTO;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class FavoriteEntity
{
    private String moimId;
    private String userId;

    public FavoriteDTO convertTo()
    {
        return FavoriteDTO.builder()
                .moimId(this.moimId)
                .userId(this.userId)
                .build();
    }
}
