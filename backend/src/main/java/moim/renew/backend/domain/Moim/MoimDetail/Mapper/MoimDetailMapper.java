package moim.renew.backend.domain.Moim.MoimDetail.Mapper;

import moim.renew.backend.domain.Moim.MoimDetail.Entity.MoimDetailEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoimDetailMapper
{
    void deletemoimdetailbyResult(String moimId);
    MoimDetailEntity selectmoimdetailbyResult(String moimId);
    void updatemoimdetailbyResult(MoimDetailEntity moimDetailEntity);
    void moimofInsert(MoimDetailEntity moimDetailEntity);
    void updateByApproval(Boolean approval, String moimId);
}
