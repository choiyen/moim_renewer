package moim.renew.backend.domain.Moim.MoimApproval.Mapper;

import moim.renew.backend.domain.Moim.MoimApproval.Entity.ApprovalEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalMapper
{
   void insertMoimApproval(ApprovalEntity approval);
   void updateMoimApprovalStatus(Integer approvalId, String status);
   ApprovalEntity selectMoimApprovalsByMoimId(String moimId);
   ApprovalEntity selectMoimApprovalsByUserId(String userId, String moimId);
   void deleteMoimApproval(Integer approvalId);
}
