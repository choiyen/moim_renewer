package moim.renew.backend.Favorite.Entity;


import lombok.*;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)  // 생성자 접근 수준을 PUBLIC으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode
@Data
public class FavoriteEntity
{
    private Integer moimId;
    private String userId;
}
