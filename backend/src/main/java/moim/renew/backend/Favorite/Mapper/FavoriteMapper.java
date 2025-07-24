package moim.renew.backend.Favorite.Mapper;

import moim.renew.backend.Favorite.Entity.FavoriteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper
{
    FavoriteEntity selectoffavorite(String userId);
    FavoriteEntity selectofmoimId(String moimId);
    void Deleteoffavorite(String moimid, String userId);
    void Insertfavoriteofmoim(FavoriteEntity favoriteEntity);
}
