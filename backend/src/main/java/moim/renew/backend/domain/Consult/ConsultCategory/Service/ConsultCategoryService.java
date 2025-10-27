package moim.renew.backend.domain.Consult.ConsultCategory.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Consult.ConsultCategory.DTO.ConsultCategoryDTO;
import moim.renew.backend.domain.Consult.ConsultCategory.Entity.ConsultCategoryEntity;
import moim.renew.backend.domain.Consult.ConsultCategory.Mapper.ConsultCategoryMapper;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.EmptyException;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsultCategoryService
{
    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;

    public ConsultCategoryDTO Insert(ConsultCategoryDTO consultCategoryDTO)
    {
        ConsultCategoryEntity consultCategoryEntity = consultCategoryDTO.ConvertTo();
        consultCategoryMapper.InsertConsultCategory(consultCategoryEntity);
        ConsultCategoryEntity consultCategoryEntity1 = consultCategoryMapper.SelectConsultCategoryId(consultCategoryEntity.getConsultCategoryId());
        System.out.println(consultCategoryEntity1);
        if(consultCategoryEntity1 != null)
        {
            return consultCategoryEntity1.ConvertTo();
        }
        else
        {
            throw new InsertException("질문용 게시판 생성에 실패하였습니다.");
        }
    }
    public List<ConsultCategoryDTO> SelectAll()
    {
        List<ConsultCategoryEntity> consultCategoryEntities = consultCategoryMapper.SelectConsultCategory();
        if(consultCategoryEntities.isEmpty())
        {
            throw new EmptyException("조회를 시도하였으나, 데이터가 비어있습니다.");
        }
        else
        {
            List<ConsultCategoryDTO> consultCategoryDTOS = consultCategoryEntities.stream()
                    .map(entity -> ConsultCategoryDTO.of(entity.getConsultCategoryId(), entity.getConsultType())) // 생성자 매핑
                    .collect(Collectors.toList());
            return consultCategoryDTOS;
        }
    }
    public ConsultCategoryDTO update(ConsultCategoryDTO consultCategoryDTO)
    {
        ConsultCategoryEntity consultCategoryEntity = consultCategoryDTO.ConvertTo();
        consultCategoryMapper.UpdateConsultCategory(consultCategoryEntity);
        ConsultCategoryEntity consultCategoryEntity1 = consultCategoryMapper.SelectConsultCategoryId(consultCategoryEntity.getConsultCategoryId());
        if(consultCategoryEntity1.equals(consultCategoryEntity))
        {
            return consultCategoryEntity1.ConvertTo();
        }
        else
        {
            throw new UpdateException("관리자 페이지 : 게시판 카테고리 생성에 실패하였습니다.");
        }
    }
    public Boolean Delete(Integer ConsultCategoryId)
    {
        ConsultCategoryEntity consultCategoryEntity = consultCategoryMapper.SelectConsultCategoryId(ConsultCategoryId);
        if(consultCategoryEntity != null)
        {
            consultCategoryMapper.DeleteConsultCategory(consultCategoryEntity.getConsultCategoryId());
            ConsultCategoryEntity consultCategoryEntity1 = consultCategoryMapper.SelectConsultCategoryId(ConsultCategoryId);
            if(consultCategoryEntity1 == null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            throw new DeleteException("해당 넘버를 가진 질문 게시판 카테고리를 찾을 수 없습니다.");
        }
    }
}
