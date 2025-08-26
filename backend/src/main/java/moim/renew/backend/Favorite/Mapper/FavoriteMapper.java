package moim.renew.backend.Favorite.Mapper;

import moim.renew.backend.Favorite.Entity.FavoriteEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteMapper
{
    List<FavoriteEntity> selectoffavorite(String userId);
    FavoriteEntity selectofmoimId(String moimId, String userId);
    void Deleteoffavorite(String moimid, String userId);
    void Insertfavoriteofmoim(FavoriteEntity favoriteEntity);
}
