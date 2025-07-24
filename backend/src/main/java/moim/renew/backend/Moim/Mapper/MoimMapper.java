package moim.renew.backend.Moim.Mapper;

import moim.renew.backend.Moim.Entity.MoimEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimMapper
{
    void DeleteMoim(String moimId);
    List<MoimEntity> SelectAllMoims();
    MoimEntity SelectMoimById();
    void UpdateMoim(MoimEntity moim);
    void InsertMoim(MoimEntity moim);
}
