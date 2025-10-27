package moim.renew.backend.domain.Moim.MoimDetail.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Moim.MoimMain.Service.MoimService;
import moim.renew.backend.domain.Moim.MoimDetail.Mapper.MoimDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoimDetailService
{
  @Autowired
  private MoimService moimService;

  @Autowired
  private MoimDetailMapper moimDetailMapper;

}
