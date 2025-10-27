package moim.renew.backend.domain.Consult.ConsultMain.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Consult.ConsultMain.DTO.ConsultDTO;
import moim.renew.backend.domain.Consult.ConsultMain.DTO.DeleteConsultDTO;
import moim.renew.backend.domain.Consult.ConsultMain.Entity.ConsultEntity;
import moim.renew.backend.domain.Consult.ConsultMain.Mapper.ConsultMapper;
import moim.renew.backend.domain.Consult.ConsultCategory.Mapper.ConsultCategoryMapper;
import moim.renew.backend.gobal.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsultService
{
    @Autowired
    private ConsultMapper consultMapper;

    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;



    public ConsultDTO Insert(ConsultDTO consultDTO)
    {
        if(consultCategoryMapper.SelectConsultCategoryId(consultDTO.getConsultCategoryId()) != null)
        {
            ConsultEntity consultEntity = consultDTO.ConvertTo();
            consultMapper.InsertConsult(consultEntity);
            ConsultEntity consultEntity1 = consultMapper.SelectConsult(consultDTO.getMoimConsultId());
            if(consultEntity1 != null)
            {
                return consultEntity1.ConvertTo();
            }
            else
            {
                throw new InsertException("문의 게시판에 데이터를 등록하였습니다.");
            }
        }
        else
        {
            throw new InsertException("해당 넘버를 가진 게시판이 존재하지 않습니다.");
        }
    }
    public ConsultDTO Update(ConsultDTO consultDTO)
    {
        ConsultEntity consultEntity = consultDTO.ConvertTo();
        if(consultMapper.SelectConsult(consultDTO.getMoimConsultId()).getNickname().equals(consultDTO.getNickname()))
        {
            consultMapper.UpdateConsult(consultEntity);
            ConsultEntity consultEntity1 = consultMapper.SelectConsult(consultDTO.getMoimConsultId());
            if(consultEntity1.equals(consultEntity))
            {
                return consultEntity1.ConvertTo();
            }
            else
            {
                throw new UpdateException("모임 홈페이지 문의 글에 대한 업데이트에 실패하였습니다.");
            }
        }
        else
        {
            throw new SelectException("모임 홈페이지 문의글을 삭제할 수 있는 권한이 없습니다. : 닉네임이 다름");
        }

    }
    public Boolean Delete(DeleteConsultDTO deleteConsultDTO)
    {
        ConsultEntity consultEntities = consultMapper.SelectConsult(deleteConsultDTO.getMoimConsultId());
        if(consultEntities != null && consultEntities.getNickname().equals(deleteConsultDTO.getNickname()))
        {
            consultMapper.DeleteConsult(deleteConsultDTO.getMoimConsultId());
            ConsultEntity consultEntity = consultMapper.SelectConsult(deleteConsultDTO.getMoimConsultId());
            if(consultEntity == null)
            {
                return true;
            }
            else
            {
                throw new DeleteException("정상적으로 모임 리뷰 데이터 삭제되지 않았습니다.");
            }
        }
        else
        {
            throw new InsertException("삭제할 대상이 존재하지 않습니다. 이미 삭제됐거나, 닉네임이 다릅니다.");
        }
    }
    public List<ConsultDTO> SelectAll(Integer CategoryId)
    {
        List<ConsultEntity> consultEntities = consultMapper.SelectConsultBYCategoryId(CategoryId);
        if(consultEntities.isEmpty())
        {
            throw new EmptyException("모임 카테고리를 검색하였으나, 현재는 비어있는 것 같습니다.");
        }
        else
        {
            List<ConsultDTO> consultDTOS = consultEntities.stream()
                    .map(ConsultEntity::ConvertTo)
                    .collect(Collectors.toList());
            return consultDTOS;
        }
    }
    public ConsultDTO Select(String moimConsultId)
    {
        ConsultEntity consultEntity = consultMapper.SelectConsult(moimConsultId);
        if(consultEntity != null)
        {
            return consultEntity.ConvertTo();
        }
        else
        {
            throw new SelectException("해당 코드를 가진 모임 홈페이지 건의 및 상담 글은 존재하지 않는 것 같아요. 관리자에게 문의하세요");
        }
    }
    public Integer SelectCount(String moimConsultId)
    {
        ConsultEntity consultEntity = consultMapper.SelectConsult(moimConsultId);
        consultMapper.UpdateViewCount(moimConsultId);
        ConsultEntity consultEntity2 = consultMapper.SelectConsult(moimConsultId);
        if(consultEntity.getViewcount() < consultEntity2.getViewcount())
        {
            return consultEntity2.getViewcount();
        }
        else
        {
            throw new UpdateException("접속 횟수 업데이트에 실패하였습니다. 죄송합니다.");
        }
    }
}
