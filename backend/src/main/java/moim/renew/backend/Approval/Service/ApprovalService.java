package moim.renew.backend.Approval.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.Approval.DTO.ApprovalDTO;
import moim.renew.backend.Approval.Entity.ApprovalEntity;
import moim.renew.backend.Approval.Mapper.ApprovalMapper;
import moim.renew.backend.Moim.Service.MoimService;
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
}
