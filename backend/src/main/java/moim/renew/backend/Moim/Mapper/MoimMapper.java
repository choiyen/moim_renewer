package moim.renew.backend.Moim.Mapper;

import moim.renew.backend.Moim.Entity.MoimEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimMapper
{
    MoimEntity SelectMoimById(String moimId);
    List<MoimEntity> SelectAllMoims();
    void InsertMoim(MoimEntity moim);
    void DeleteMoim(String moimId, String organizer);
    void UpdateMoim(MoimEntity moim);

}
