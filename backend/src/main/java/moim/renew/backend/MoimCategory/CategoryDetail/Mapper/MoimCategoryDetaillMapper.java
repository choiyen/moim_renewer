package moim.renew.backend.MoimCategory.CategoryDetail.Mapper;

import moim.renew.backend.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimCategoryDetaillMapper
{
    MoimCategoryDetailEntity selectCategoryDetailById(String categoryDetailId);
    List<MoimCategoryDetailEntity> selectCategoryDetailByCategoryId(Integer categoryId);
    void insertCategoryDetail(MoimCategoryDetailEntity moimCategoryDetailEntity);
    void updateCategoryDetail(MoimCategoryDetailEntity moimCategoryDetailEntity);
    void deleteCategoryDetail(String categoryDetailId);
}
