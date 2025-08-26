package moim.renew.backend.ConsultComment.Mapper;

import moim.renew.backend.ConsultComment.Entity.ConsultCommentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsultCommentMapper
{
    void InsertConsultComment(ConsultCommentEntity consultCommentEntity);
    void UpdateConsultComment(ConsultCommentEntity consultCommentEntity);
    ConsultCommentEntity SelectConsultComment(String moimConsultId);
    void DeleteConsultComment(String moimConsultCommentId);
}
