package moim.renew.backend.domain.Moim.MoimMain.Mapper;

import moim.renew.backend.domain.Moim.MoimMain.Entity.MoimEntity;
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
    Boolean ApprovalByMoimId(String moimId);
}
