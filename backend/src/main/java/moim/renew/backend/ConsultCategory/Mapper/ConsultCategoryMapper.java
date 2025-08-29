package moim.renew.backend.ConsultCategory.Mapper;

import moim.renew.backend.ConsultCategory.Entity.ConsultCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultCategoryMapper
{
    void InsertConsultCategory(ConsultCategoryEntity consultCategoryEntity);
    void UpdateConsultCategory(ConsultCategoryEntity consultCategoryEntity);
    void DeleteConsultCategory(Integer consultCategoryId);
    List<ConsultCategoryEntity> SelectConsultCategory();
    ConsultCategoryEntity SelectConsultCategoryId(Integer consultCategoryId);
}
