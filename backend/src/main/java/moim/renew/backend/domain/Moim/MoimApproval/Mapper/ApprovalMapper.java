package moim.renew.backend.domain.Moim.MoimApproval.Mapper;

import moim.renew.backend.domain.Moim.MoimApproval.Entity.ApprovalEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalMapper
{
   void insertMoimApproval(ApprovalEntity approval);
   void updateMoimApprovalStatus(long approvalId, String status);
   List<ApprovalEntity> selectMoimApprovalsByMoimId(String moimId);
    ApprovalEntity selectMoimApprovalsByUserId(String userId, String moimId);
   void deleteMoimApproval(long approvalId);
}
