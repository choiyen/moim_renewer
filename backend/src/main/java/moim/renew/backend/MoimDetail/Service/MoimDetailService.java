package moim.renew.backend.MoimDetail.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.Approval.Service.ApprovalService;
import moim.renew.backend.Moim.DTO.MoimDTO;
import moim.renew.backend.Moim.Service.MoimService;
import moim.renew.backend.MoimDetail.Entity.MoimDetailEntity;
import moim.renew.backend.MoimDetail.Mapper.MoimDetailMapper;
import moim.renew.backend.config.Exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MoimDetailService
{
  @Autowired
  private MoimService moimService;

  @Autowired
  private MoimDetailMapper moimDetailMapper;

}
