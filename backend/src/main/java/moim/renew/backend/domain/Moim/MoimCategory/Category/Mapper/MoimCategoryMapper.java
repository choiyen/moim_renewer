package moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper;

import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimCategoryMapper
{
    void  InsertCategory(MoimCateGoryEntity cateGoryEntity);
    int  UpdateCategory(MoimCateGoryEntity moimCateGoryEntity);
    List<MoimCateGoryEntity> SelectCategory();
    void  DeleteCategory(Integer categoryId);
    MoimCateGoryEntity GetByCategoryId(Integer categoryId);
}
