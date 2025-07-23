package moim.renew.backend.Favorite.DTO;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class FavoriteDTO
{
    private Integer moimId;
    private String userId;
}
