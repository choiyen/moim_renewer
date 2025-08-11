package moim.renew.backend.Approval.Mapper;

import moim.renew.backend.Approval.DTO.ApprovalDTO;
import moim.renew.backend.Approval.Entity.ApprovalEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalMapper
{
    void moimApprovalofInsert(ApprovalEntity approvalDTO);
    void updatemoimApprovalbyResult(ApprovalEntity approvalDTO);
    void JoinmoimApproval(String joinmember);
    ApprovalEntity selectmoimApprovalbyResult(String moimId);
    void deletemoimApprovalbyResult(String moimId);
}
