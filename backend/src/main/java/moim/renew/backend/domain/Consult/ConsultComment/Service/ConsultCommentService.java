package moim.renew.backend.domain.Consult.ConsultComment.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Consult.ConsultMain.Mapper.ConsultMapper;
import moim.renew.backend.domain.Consult.ConsultComment.DTO.ConsultCommentDTO;
import moim.renew.backend.domain.Consult.ConsultComment.DTO.DeletedCommentDTO;
import moim.renew.backend.domain.Consult.ConsultComment.Entity.ConsultCommentEntity;
import moim.renew.backend.domain.Consult.ConsultComment.Mapper.ConsultCommentMapper;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.SelectException;
import moim.renew.backend.gobal.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsultCommentService
{
    @Autowired
    private ConsultCommentMapper consultCommentMapper;

    @Autowired
    private ConsultMapper consultMapper;

    public ConsultCommentDTO Insert(ConsultCommentDTO consultCommentDTO)
    {
        if(consultMapper.SelectConsult(consultCommentDTO.getMoimConsultId()) != null)
        {
            ConsultCommentEntity consultCommentEntity = consultCommentDTO.convertTo();
            System.out.println(consultCommentEntity);
            consultCommentMapper.InsertConsultComment(consultCommentEntity);
            System.out.println(consultCommentEntity.getMoimConsultCommentId());
            ConsultCommentEntity consultComment = consultCommentMapper.SelectConsultComment(consultCommentEntity.getMoimConsultCommentId());
            System.out.println(consultComment);
            if(consultComment != null)
            {

                return  consultComment.convertTo();
            }
            else
            {
                throw new InsertException("댓글 데이터 생성에 실패하였습니다.");
            }
        }
        else
        {
            throw new InsertException("서버 상에서 댓글 데이터와 일치하는 게시판을 찾을 수 없습니다.");
        }
    }
    public ConsultCommentDTO Update(ConsultCommentDTO consultCommentDTO)
    {
        ConsultCommentEntity consultCommentEntity = consultCommentMapper.SelectConsultComment(consultCommentDTO.getMoimConsultCommentId());
        if(consultCommentEntity != null)
        {
            consultCommentMapper.UpdateConsultComment(consultCommentDTO.convertTo());
            ConsultCommentEntity consultCommentEntity2 = consultCommentMapper.SelectConsultComment(consultCommentDTO.getMoimConsultCommentId());
            System.out.println(consultCommentEntity2);
            if(consultCommentEntity2.equals(consultCommentDTO.convertTo()))
            {
                return consultCommentEntity2.convertTo();
            }
            else
            {
                throw new UpdateException("업데이트 대상을 찾을 수 없습니다.");
            }
        }
        else
        {
            throw new SelectException("업데이트 대상을 찾을 수 없습니다.");
        }
    }
    public Boolean Delete(DeletedCommentDTO deletedCommentDTO)
    {
        ConsultCommentEntity consultCommentEntity = consultCommentMapper.SelectConsultComment(deletedCommentDTO.getMoimConsultCommentId());
        if(consultCommentEntity != null && consultCommentEntity.getPassword().equals(deletedCommentDTO.getPassword()))
        {
            System.out.println(consultCommentEntity);
            consultCommentMapper.DeleteConsultComment(deletedCommentDTO.getMoimConsultCommentId(), deletedCommentDTO.getPassword());
            if(consultCommentMapper.SelectConsultComment(deletedCommentDTO.getMoimConsultCommentId()) != null)
            {
               return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            throw new DeleteException("삭제할 대상이 되는 데이터를 찾을 수 없습니다.");
        }
    }
    public ConsultCommentDTO Select(String MoimConsultCommentId)
    {
        ConsultCommentEntity consultCommentEntity = consultCommentMapper.SelectConsultComment(MoimConsultCommentId);
        if(consultCommentEntity != null)
        {
            return consultCommentEntity.convertTo();
        }
        else
        {
            throw new SelectException("Q&A 댓글 데이터를 찾을 수 없습니다.");
        }
    }
    public List<ConsultCommentDTO> SelectAll(String MoimConsultId)
    {
        List<ConsultCommentEntity> consultCommentEntities = consultCommentMapper.SelectConsultComments(MoimConsultId);
        if(consultCommentEntities.isEmpty())
        {
            throw new SelectException("List 조회를 시도했으나, 댓글이 비어있습니다..");
        }
        else
        {
            List<ConsultCommentDTO> consultCommentDTOList = consultCommentEntities.stream()
                    .map(ConsultCommentEntity::convertTo) // ← 엔티티를 DTO로 변환하는 메서드
                    .collect(Collectors.toList());
            return consultCommentDTOList;
        }
    }
}
