package moim.renew.backend.Consult.Mapper;

import moim.renew.backend.Consult.Entity.ConsultEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultMapper
{
    ConsultEntity SelectConsult(String moimConsultId);
    void InsertConsult(ConsultEntity consultEntity);
    void UpdateConsult(ConsultEntity consultEntity);
    void DeleteConsult(String moimConsultId);
    List<ConsultEntity> SelectConsultBYCategoryId(Integer consultCategoryId);
}
