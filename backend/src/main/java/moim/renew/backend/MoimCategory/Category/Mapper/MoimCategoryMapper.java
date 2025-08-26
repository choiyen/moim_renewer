package moim.renew.backend.MoimCategory.Category.Mapper;

import moim.renew.backend.MoimCategory.Category.Entity.MoimCateGoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimCategoryMapper
{
    void  InsertCategory(MoimCateGoryEntity cateGoryEntity);
    void UpdateCategory(MoimCateGoryEntity moimCateGoryEntity);
    List<MoimCateGoryEntity> SelectCategory();
    void  DeleteCategory(String categoryId);

}
