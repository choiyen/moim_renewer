package moim.renew.backend.ConsultComment.Mapper;

import moim.renew.backend.ConsultComment.Entity.ConsultCommentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultCommentMapper
{
    void InsertConsultComment(ConsultCommentEntity consultCommentEntity);
    void UpdateConsultComment(ConsultCommentEntity consultCommentEntity);
    void DeleteConsultComment(String moimConsultCommentId);
    List<ConsultCommentEntity> SelectConsultComments(String moimConsultId);
    ConsultCommentEntity SelectConsultComment(String moimConsultId);

}
