package moim.renew.backend.domain.Moim.MoimApproval.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Moim.MoimApproval.Mapper.ApprovalMapper;
import moim.renew.backend.domain.Moim.MoimMain.Service.MoimService;
import moim.renew.backend.domain.Moim.MoimDetail.Service.MoimDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
