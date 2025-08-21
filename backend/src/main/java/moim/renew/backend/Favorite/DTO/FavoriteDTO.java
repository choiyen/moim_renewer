package moim.renew.backend.Favorite.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "지정된 모임이 없으면 모임 찜 목록 등록을 할 수 없습니다.")
    private String moimId;
    @NotBlank(message = "데이터 관리를 위하여 유저 정보를 반드시 포함하여야 합니다.")
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
