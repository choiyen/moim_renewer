package moim.renew.backend.MoimCategory.Category.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.ConsultCategory.DTO.ConsultCategoryDTO;
import moim.renew.backend.ConsultCategory.Entity.ConsultCategoryEntity;
import moim.renew.backend.MoimCategory.Category.DTO.MoimCateGoryDTO;
import moim.renew.backend.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.config.Exception.InsertException;
import moim.renew.backend.config.Exception.SelectException;
import moim.renew.backend.config.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MoimCateGoryService
{
    @Autowired
    MoimCategoryMapper mapper;

    public MoimCateGoryDTO Insert(MoimCateGoryDTO moimCateGoryDTO)
    {
        MoimCateGoryEntity moimCateGoryEntity = moimCateGoryDTO.ConvertTo();
        mapper.InsertCategory(moimCateGoryEntity);
        MoimCateGoryEntity moimCateGoryEntity1 = mapper.GetByCategoryId(moimCateGoryDTO.getCategoryId());
        if (moimCateGoryEntity1 != null)
        {
            log.info("정상적으로 모임 카테고리 생성에 성공하였습니다.");
            return moimCateGoryEntity1.ConvertTo();
        }
        else
        {
            log.warn("모임 카테고리 생성에 실패하였으니, 개발자에게 문의하세요");
            throw new InsertException("모임 카테고리 생성에 실패하였습니다.");
        }
    }
    public MoimCateGoryDTO Update(MoimCateGoryDTO moimCateGoryDTO)
    {
        MoimCateGoryEntity moimCateGoryEntity = moimCateGoryDTO.ConvertTo();
        mapper.UpdateCategory(moimCateGoryEntity);
        MoimCateGoryEntity moimCateGoryEntity1 = mapper.GetByCategoryId(moimCateGoryEntity.getCategoryId());
        if(moimCateGoryEntity1.equals(moimCateGoryEntity))
        {
            log.info("모임 카테고리 업데이트에 성공하였습니다.");
            return moimCateGoryEntity1.ConvertTo();
        }
        else
        {
            log.warn("모임 카테고리 업데이트에 실패하였습니다. 개발자에게 문의하세요");
            throw new UpdateException("데이터 업데이트에 실패하였습니다.");
        }
    }
    public Boolean Delete(Integer moimCategoryId)
    {
        MoimCateGoryEntity moimCateGoryEntity = mapper.GetByCategoryId(moimCategoryId);
        if(moimCateGoryEntity != null)
        {
            mapper.DeleteCategory(moimCategoryId);
            MoimCateGoryEntity moimCateGoryEntity2 = mapper.GetByCategoryId(moimCategoryId);
            if(moimCateGoryEntity2 == null)
            {
                log.info("모임 카테고리 데이터 삭제에 성공!!!");
                return true;
            }
            else
            {
                log.error("정상적으로 모임 카테고리가 제거되지 않았습니다. 관리자에게 문의 바람");
                return false;
            }
        }
        else
        {
            log.warn("제거 대상이 되는 넘버의 카테고리가 존재하지 않거나, 원래부터 없습니다");
            throw new SelectException("해당 넘버를 가진 모임 대분류 카테고리가 존재하지 않습니다.");
        }
    }
    public List<MoimCateGoryDTO> SelectAll()
    {
        List<MoimCateGoryEntity> moimCateGoryEntities = mapper.SelectCategory();
        if(moimCateGoryEntities.isEmpty())
        {
            log.warn("조회를 시도하였으나, 데이터가 현재 비어있습니다.");
            throw new SelectException("조회를 시도하였으나, 데이터가 비어있습니다.");
        }
        else
        {

            List<MoimCateGoryDTO> moimCateGoryDTOS = moimCateGoryEntities.stream()
                    .map(entity -> MoimCateGoryDTO.of(entity.getCategoryId(), entity.getCategorisation())) // 생성자 매핑
                    .collect(Collectors.toList());
            log.info("정상적으로 모임 카테고리 조회에 성공하였습니다.");
            return moimCateGoryDTOS;
        }
    }
}
