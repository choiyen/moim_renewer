package moim.renew.backend.Approval.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.Approval.DTO.ApprovalDTO;
import moim.renew.backend.Approval.Entity.ApprovalEntity;
import moim.renew.backend.Approval.Mapper.ApprovalMapper;
import moim.renew.backend.Moim.DTO.MoimDTO;
import moim.renew.backend.Moim.Entity.MoimEntity;
import moim.renew.backend.Moim.Service.MoimService;
import moim.renew.backend.MoimDetail.Entity.MoimDetailEntity;
import moim.renew.backend.MoimDetail.Service.MoimDetailService;
import moim.renew.backend.config.Exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ApprovalService
{
    @Autowired
    private ApprovalMapper approvalMapper;

    @Autowired
    private MoimService moimService;

    @Autowired
    private MoimDetailService moimDetailService;

    public ApprovalDTO Insert(ApprovalDTO approvalDTO)
    {
        Boolean bool = moimService.isApproved(approvalDTO.getMoimId());
        if(bool)
        {
            approvalMapper.moimApprovalofInsert(approvalDTO.ConvertTo());
            ApprovalEntity approval = approvalMapper.selectmoimApprovalbyResult(approvalDTO.getMoimId());
            if(approval != null)
            {
                return  approval.ConvertTo();
            }
            else
            {
                throw new InsertException("정상적으로 데이터가 저장되지 않았습니다 : 모임 가입 목록");
            }
        }
        else
        {
            throw new InsertException("모임 승인 기능이 없이 자동 가입되는 모임입니다.");
        }
    }
    private boolean JoinmoimApproval(String MoimId, String JoinMember)
    {
        Boolean bool = moimService.isApproved(MoimId);
        if(bool)
        {
            approvalMapper.JoinmoimApproval(JoinMember);
            ApprovalEntity approval = approvalMapper.selectmoimApprovalbyResult(MoimId);
            if(approval != null)
            {
                System.out.println(approval);
                return  true;
            }
            else
            {
                throw new InsertException("정상적으로 데이터가 저장되지 않았습니다 : 모임 가입 목록");
            }
        }
        else
        {
            System.out.println("승인 기능이 적용된 모임이 아닙니다. 자동으로 가입됩니다.");
            List<Object> list = moimDetailService.JoinMoim(MoimId, JoinMember);
            if(list.isEmpty())
            {
                throw new InsertException("정보를 승인하는 도중 예기치 못한 에러 발생");
            }
            else
            {
                return true;
            }
        }
    }
//    private boolean JoinMoimSign(String MoimId, String userId)
//    {
//        MoimDTO moim = moimService.SelectById(MoimId);
//        if(moim.getOrganizer().equals(userId))
//        {
//
//        }
//        else
//        {
//            throw new InsertException("모임의 가승인 명부를 수정할 권한이 없습니다.");
//        }
//    }

}
