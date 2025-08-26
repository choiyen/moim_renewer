package moim.renew.backend.Approval.Mapper;

import moim.renew.backend.Approval.Entity.ApprovalEntity;
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
