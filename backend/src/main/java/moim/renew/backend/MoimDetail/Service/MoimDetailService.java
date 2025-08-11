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

  @Autowired
  private ApprovalService approvalService;

  public List<Object> JoinMoim(String moimId, String member)
  {
     MoimDTO moimDTO = moimService.SelectById(moimId);
      MoimDetailEntity moimDetailEntity = moimDetailMapper.selectmoimdetailbyResult(moimId);
     if(!moimDTO.isApproval())
     {
         if(moimDTO.getMaxPeople() <= moimDetailEntity.getMember().size())
         {
             List<String> list = new ArrayList<>(moimDetailEntity.getMember());
             list.add(member);
             moimDetailMapper.JoinMoim(list);
             MoimDetailEntity moimDetailEntity1 = moimDetailMapper.selectmoimdetailbyResult(moimId);
             if(list.equals(moimDetailEntity1.getMember()))
             {
                 List<Object> list1 = new ArrayList<>();
                 list1.add(moimDTO);
                 list1.add(moimDetailEntity1);
                 return list1;
             }
             else
             {
                throw new InsertException("정상적으로 가입자 정보가 모임 데이터에 등록되지 않았습니다.");
             }
         }
         else
         {
             throw new InsertException("모임 가입자가 설정하신 최대 가입자수를 초과할 수 없습니다.");
         }
     }
     else
     {
         throw new InsertException("모임 가가입 명단에 먼저 등록되어야 합니다.");
     }
  }
}
