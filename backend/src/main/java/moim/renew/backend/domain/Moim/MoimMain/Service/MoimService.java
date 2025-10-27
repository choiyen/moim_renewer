package moim.renew.backend.domain.Moim.MoimMain.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Moim.MoimMain.DTO.MoimDTO;
import moim.renew.backend.domain.Moim.MoimMain.Entity.MoimEntity;
import moim.renew.backend.domain.Moim.MoimMain.Mapper.MoimMapper;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.SelectException;
import moim.renew.backend.gobal.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MoimService {
    @Autowired
    MoimMapper moimMapper;

    public MoimDTO SelectById(String moimId) {
        MoimEntity moim = moimMapper.SelectMoimById(moimId);
        if (moim == null)
        {
            log.error("해당하는 모임 코드를 지닌 Moim 정보를 찾을 수 없습니다.");
            throw new SelectException();
        }
        else
        {
            log.info("해당되는 모임 ID를 지닌 모임 정보를 찾았습니다.");
            return moim.convertTo();
        }
    }

    public List<MoimDTO> SelectAll()
    {
        List<MoimEntity> moimEntities = moimMapper.SelectAllMoims();
        List<MoimDTO> moimDTOS = new ArrayList<>();
        if (moimEntities.isEmpty())
        {
            log.warn("해당되는 모임 정보를 찾았으나, 그 정보가 비어있는 것 같습니다.");
            throw new SelectException("전체 모임 정보를 조회하였으나, 현재 개설된 모임이 없는 것 같습니다.");
        }
        else
        {
            log.info("모임 정보를 찾았습니다, 프론트엔드로 서버로 송출하겠습니다.");
            for (MoimEntity moim : moimEntities)
            {
                moimDTOS.add(moim.convertTo());
            }
            return moimDTOS;
        }
    }

    public MoimDTO Insert(MoimDTO moimDTO)
    {
        MoimEntity moim = moimMapper.SelectMoimById(moimDTO.getMoimId());
        if(moim == null)
        {
            moimMapper.InsertMoim(moimDTO.convertTo());
            MoimEntity moim1 = moimMapper.SelectMoimById(moimDTO.getMoimId());
            if(moim1 == null)
            {
                log.error("예기치 못한 오류로 모임 정보를 찾을 수 없습니다.");
                throw new InsertException();
            }
            else
            {
                log.info("정상적으로 모임 정보 생성에 성공하였습니다.");
                return moim1.convertTo();
            }
        }
        else
        {
            throw new SelectException("해당 MoimId를 가진 모임이 이미 존재합니다. 다른 레코드를 발급받으세요.");
        }
    }
    public MoimDTO Update(MoimDTO moimDTO)
    {
        MoimEntity oldmoim = moimMapper.SelectMoimById(moimDTO.getMoimId());
        if(moimDTO.getOrganizer().equals(oldmoim.getOrganizer()))
        {
            MoimEntity moim = moimDTO.convertTo().convertToReNew(oldmoim);
            moimMapper.UpdateMoim(moim);
            MoimEntity moim1 = moimMapper.SelectMoimById(moim.getMoimId());
            if(moim.equals(moim1))
            {
                log.info("정상적을로 모임 정보 변경에 성공하였습니다.");
                return moim1.convertTo();
            }
            else
            {
                log.error("모임 정보 변경에 실패하였습니다. 다시 시도하여 주세요.");
                throw new UpdateException();
            }
        }
        else
        {
            throw new InsertException("모임 데이터 변경에 실패하였습니다. 데이터를 변경할 권한이 없습니다.");
        }
    }
    public Boolean Delete(String moimId, String userId)
    {
        MoimEntity moim = moimMapper.SelectMoimById(moimId);
        if(moim == null)
        {
            log.error("해당하는 모임 코드를 지닌 모임을 찾을 수 없습니다.");
            throw new SelectException("해당하는 모임 코드를 지닌 모임을 찾을 수 없습니다.");
        }
        else
        {
            moimMapper.DeleteMoim(moimId, userId);
            MoimEntity moim1 = moimMapper.SelectMoimById(moimId);
            if (moim1 != null)
            {
                log.error("모임 정보가 없거나, 모임을 삭제할 수 있는 모임의 개설자가 아닙니다.");
                throw new DeleteException("모임 정보가 없거나, 모임을 삭제할 수 있는 모임의 개설자가 아닙니다.");
            }
            else
            {
                log.info("정상적으로 모임 정보 삭제에 성공하였습니다.");
                return true;
            }
        }
    }
    public boolean isApproved(String moimId) {
        Boolean approved = moimMapper.ApprovalByMoimId(moimId);
        return approved != null && approved;
    }

}
