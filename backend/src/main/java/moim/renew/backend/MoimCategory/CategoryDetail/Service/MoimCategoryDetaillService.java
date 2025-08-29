package moim.renew.backend.MoimCategory.CategoryDetail.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.MoimCategory.Category.DTO.MoimCateGoryDTO;
import moim.renew.backend.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.MoimCategory.CategoryDetail.DTO.MoimCategoryDetailDTO;
import moim.renew.backend.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.config.Exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoimCategoryDetaillService
{
    @Autowired
    MoimCategoryMapper moimCategoryMapper;

    @Autowired
    MoimCategoryDetaillMapper moimCategoryDetaillMapper;

    public List<MoimCategoryDetailDTO> SelectAll(String categoryId)
    {
        List<MoimCategoryDetailEntity> moimCategoryDetailEntities = moimCategoryDetaillMapper.selectCategoryDetailByCategoryId(categoryId);
        if(moimCategoryDetailEntities.isEmpty())
        {
            log.warn("조회를 시도하였으나, 데이터가 현재 비어있습니다.");
            throw new SelectException("조회를 시도하였으나, 데이터가 비어있습니다.");
        }
        else
        {

            List<MoimCategoryDetailDTO> moimCateGoryDTOS = moimCategoryDetailEntities.stream()
                    .map(entity -> MoimCategoryDetailDTO.of(entity.getCategoryDetailId(),entity.getCategoryId(), entity.getCategorisationDetail())) // 생성자 매핑
                    .collect(Collectors.toList());
            log.info("정상적으로 대분류에 소속된 소분류 카테고리 조회에 성공하였습니다.");
            return moimCateGoryDTOS;
        }
    }

    public MoimCategoryDetailDTO Insert(MoimCategoryDetailDTO moimCategoryDetailDTO)
    {
        if(moimCategoryMapper.GetByCategoryId(moimCategoryDetailDTO.getCategoryId()) != null)
        {
            MoimCategoryDetailEntity moimCategoryDetailEntity = moimCategoryDetailDTO.ConvertTo();
            moimCategoryDetaillMapper.insertCategoryDetail(moimCategoryDetailEntity);
            MoimCategoryDetailEntity moimCategoryDetailEntity2 = moimCategoryDetaillMapper.selectCategoryDetailById(moimCategoryDetailEntity.getCategoryDetailId());
            if(moimCategoryDetailEntity2 != null)
            {
                return moimCategoryDetailEntity2.ConvertTo();
            }
            else
            {
                throw new SelectException();
            }
        }
        else
        {
            throw new SelectException("해당 넘버를 가진 대분류 카테고리를 찾을 수 없습니다. 소분류를 추가할 수 없어요");
        }
    }
    public MoimCategoryDetailDTO Update(MoimCategoryDetailDTO moimCategoryDetailDTO)
    {
        if(moimCategoryMapper.GetByCategoryId(moimCategoryDetailDTO.getCategoryId()) != null)
        {
            MoimCategoryDetailEntity moimCategoryDetailEntity = moimCategoryDetailDTO.ConvertTo();
            moimCategoryDetaillMapper.updateCategoryDetail(moimCategoryDetailEntity);
            MoimCategoryDetailEntity moimCategoryDetailEntity2 = moimCategoryDetaillMapper.selectCategoryDetailById(moimCategoryDetailEntity.getCategoryDetailId());
            if(moimCategoryDetailEntity2.equals(moimCategoryDetailEntity))
            {
                return moimCategoryDetailEntity2.ConvertTo();
            }
            else
            {
                throw new SelectException();
            }
        }
        else
        {
            throw new SelectException("해당 넘버를 가진 대분류 카테고리를 찾을 수 없습니다. 소분류를 추가할 수 없어요");
        }
    }
    public Boolean Delete(String moimCategoryDetailId)
    {
        MoimCategoryDetailEntity moimCategoryDetailEntity = moimCategoryDetaillMapper.selectCategoryDetailById(moimCategoryDetailId);
        if(moimCategoryDetailEntity != null)
        {
            moimCategoryDetaillMapper.deleteCategoryDetail(moimCategoryDetailId);
            MoimCategoryDetailEntity moimCategoryDetailEntity2 = moimCategoryDetaillMapper.selectCategoryDetailById(moimCategoryDetailId);
            if(moimCategoryDetailEntity2 == null)
            {
                log.info("모임 소분류 데이터 삭제에 성공!!!");
                return true;
            }
            else
            {
                log.error("정상적으로 모임 소분류 카테고리가 제거되지 않았습니다. 관리자에게 문의 바람");
                return false;
            }
        }
        else
        {
            log.warn("제거 대상이 되는 넘버의 카테고리가 존재하지 않거나, 원래부터 없습니다");
            throw new SelectException("해당 넘버를 가진 모임 대분류 카테고리가 존재하지 않습니다.");
        }
    }
}
