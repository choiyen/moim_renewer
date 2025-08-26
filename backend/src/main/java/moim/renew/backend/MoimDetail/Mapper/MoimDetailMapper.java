package moim.renew.backend.MoimDetail.Mapper;

import moim.renew.backend.MoimDetail.Entity.MoimDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimDetailMapper
{
    void deletemoimdetailbyResult(String moimId);
    MoimDetailEntity selectmoimdetailbyResult(String moimId);
    void updatemoimdetailbyResult(MoimDetailEntity moimDetailEntity);
    void moimofInsert(MoimDetailEntity moimDetailEntity);
    void updateByApproval(String approval, String moimId);
}
