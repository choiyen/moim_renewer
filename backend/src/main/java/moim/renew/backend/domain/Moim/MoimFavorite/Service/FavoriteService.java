package moim.renew.backend.domain.Moim.MoimFavorite.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Moim.MoimFavorite.DTO.FavoriteDTO;
import moim.renew.backend.domain.Moim.MoimFavorite.Entity.FavoriteEntity;
import moim.renew.backend.domain.Moim.MoimFavorite.Mapper.FavoriteMapper;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FavoriteService
{
    @Autowired
    private FavoriteMapper favoriteMapper;

    public List<FavoriteDTO> selectoffavorite(String userId)
    {
        List<FavoriteEntity> favoriteEntities = favoriteMapper.selectoffavorite(userId);
        List<FavoriteDTO> favoriteDTOS = favoriteEntities.stream()
                .map(FavoriteEntity::convertTo) // 또는 data -> data.convertTo()
                .toList();
        return favoriteDTOS;
    }
    public FavoriteDTO Insertfavoriteofmoim(FavoriteDTO favoriteDTO)
    {
        favoriteMapper.Insertfavoriteofmoim(favoriteDTO.convertTo());
        FavoriteEntity favoriteEntity = favoriteMapper.selectofmoimId(favoriteDTO.getMoimId(), favoriteDTO.getUserId());
        if(favoriteEntity == null)
        {
            throw new InsertException("찜 목록 생성에 실패하였습니다.");
        }
        else
        {
            return favoriteEntity.convertTo();
        }
    }
    public Boolean Deleteoffavorite(String moimid, String userId)
    {
        FavoriteEntity favoriteEntity = favoriteMapper.selectofmoimId(moimid, userId);
        if(favoriteEntity != null)
        {
            favoriteMapper.Deleteoffavorite(moimid, userId);
            FavoriteEntity favoriteEntity2 = favoriteMapper.selectofmoimId(moimid, userId);

            if(favoriteEntity2 != null)
            {
                throw new InsertException("찜 목록 생성에 실패하였습니다.");
            }
            else
            {
                return true;
            }
        }
        else
        {
            throw new DeleteException("찜 목록 데이터가 존재하지 않아, 삭제할 정보가 없습니다.");
        }
    }

}
