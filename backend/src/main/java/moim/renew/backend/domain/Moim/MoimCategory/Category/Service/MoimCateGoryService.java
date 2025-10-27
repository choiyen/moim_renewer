package moim.renew.backend.domain.Moim.MoimCategory.Category.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Moim.MoimCategory.Category.DTO.MoimCateGoryDTO;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.gobal.Exception.EmptyException;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.SelectException;
import moim.renew.backend.gobal.Exception.UpdateException;
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

    public MoimCateGoryDTO Insert(MoimCateGoryDTO moimCateGoryDTO) {
        // DTO -> Entity 변환
        MoimCateGoryEntity entity = moimCateGoryDTO.ConvertTo();

        // Insert 실행 (자동 PK 주입)
        mapper.InsertCategory(entity);

        // Insert 직후 PK 확인
        if (entity.getCategoryId() != null) {
            log.info("정상적으로 모임 카테고리 생성에 성공하였습니다.");
            return entity.ConvertTo(); // 바로 DTO 반환
        } else {
            log.warn("모임 카테고리 생성에 실패하였으니, 개발자에게 문의하세요");
            throw new InsertException("모임 카테고리 생성에 실패하였습니다.");
        }
    }

    public MoimCateGoryDTO Update(MoimCateGoryDTO moimCateGoryDTO) {
        MoimCateGoryEntity moimCateGoryEntity = moimCateGoryDTO.ConvertTo();

        int updatedRows = mapper.UpdateCategory(moimCateGoryEntity);
        if (updatedRows == 0) {
            log.warn("업데이트 실패: categoryId={} 가 존재하지 않음", moimCateGoryEntity.getCategoryId());
            throw new UpdateException("해당 categoryId가 존재하지 않아 업데이트 실패");
        }

        MoimCateGoryEntity updatedEntity = mapper.GetByCategoryId(moimCateGoryEntity.getCategoryId());
        if (updatedEntity == null) {
            log.error("업데이트는 되었으나 DB에서 categoryId={} 조회 실패", moimCateGoryEntity.getCategoryId());
            throw new UpdateException("업데이트 후 조회 실패");
        }

        log.info("업데이트 성공: {}", updatedEntity);
        return updatedEntity.ConvertTo();
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
    public Boolean SelectCounting(Integer moimCategoryId)
    {
        MoimCateGoryEntity MoimCateGoryEntity = mapper.GetByCategoryId(moimCategoryId);
        if(MoimCateGoryEntity != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public List<MoimCateGoryDTO> SelectAll()
    {
        List<MoimCateGoryEntity> moimCateGoryEntities = mapper.SelectCategory();
        if(moimCateGoryEntities.isEmpty())
        {
            log.warn("조회를 시도하였으나, 데이터가 현재 비어있습니다.");
            throw new EmptyException("조회를 시도하였으나, 데이터가 비어있습니다.");
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
