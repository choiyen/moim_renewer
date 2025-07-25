package moim.renew.backend.Moim.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.Moim.DTO.MoimDTO;
import moim.renew.backend.Moim.Entity.MoimEntity;
import moim.renew.backend.Moim.Mapper.MoimMapper;
import moim.renew.backend.config.Exception.DeleteException;
import moim.renew.backend.config.Exception.InsertException;
import moim.renew.backend.config.Exception.SelectException;
import moim.renew.backend.config.Exception.UpdateException;
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
        if (moim == null) {
            throw new SelectException();
        } else {
            return moim.convertTo();
        }
    }

    public List<MoimDTO> SelectAll() {
        List<MoimEntity> moimEntities = moimMapper.SelectAllMoims();
        List<MoimDTO> moimDTOS = new ArrayList<>();
        if (moimEntities.isEmpty()) {
            throw new SelectException("전체 모임 정보를 조회하였으나, 현재 개설된 모임이 없는 것 같습니다.");
        } else {
            for (MoimEntity moim : moimEntities) {
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
                throw new InsertException();
            }
            else
            {
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
        MoimEntity moim = moimDTO.convertTo().convertToReNew(oldmoim);
        moimMapper.UpdateMoim(moim);
        MoimEntity moim1 = moimMapper.SelectMoimById(moim.getMoimId());
        if(moim.equals(moim1))
        {
            return moim1.convertTo();
        }
        else
        {
            throw new UpdateException();
        }
    }
    public Boolean Delete(String moimId, String userId)
    {
        MoimEntity moim = moimMapper.SelectMoimById(moimId);
        if(moim == null)
        {
            throw new SelectException();
        }
        else
        {
            moimMapper.DeleteMoim(moimId, userId);
            MoimEntity moim1 = moimMapper.SelectMoimById(moimId);
            if (moim1 != null)
            {
                throw new DeleteException();
            }
            else
            {
                return true;
            }
        }
    }
}
