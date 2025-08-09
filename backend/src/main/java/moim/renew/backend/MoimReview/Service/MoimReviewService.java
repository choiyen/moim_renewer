package moim.renew.backend.MoimReview.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.MoimReview.DTO.MoimReviewDTO;
import moim.renew.backend.MoimReview.Entity.MoimReviewEntity;
import moim.renew.backend.MoimReview.Mapper.MoimReviewMapper;
import moim.renew.backend.config.Exception.DeleteException;
import moim.renew.backend.config.Exception.InsertException;
import moim.renew.backend.config.Exception.SelectException;
import moim.renew.backend.config.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoimReviewService
{
    @Autowired
    MoimReviewMapper moimReviewMapper;

    public MoimReviewDTO getReviewByMoimId(String moimId) {
        MoimReviewEntity moimReviewEntity = moimReviewMapper.getReviewsByMoimId(moimId);
        if (moimReviewEntity == null) {
            throw new SelectException();
        }
        else
        {
            return moimReviewEntity.ConvertTo();
        }
    }

    public double getAverAgeScore(String moimId)
    {
        double averageScore = moimReviewMapper.getAverageScoreByMoimId(moimId);
        if(averageScore > 0)
        {
            return averageScore;
        }
        else
        {
            return 0;
        }
    }


    public MoimReviewDTO updateofScore(String moimId, Float score)
    {
        MoimReviewEntity NewReviewEntity = moimReviewMapper.getReviewsByMoimId(moimId).ConvertToNewScore(score);
        moimReviewMapper.updateofscore(NewReviewEntity);
        MoimReviewEntity moimReviewEntity1 = moimReviewMapper.getReviewsByMoimId(moimId);
        if(moimReviewEntity1.equals(NewReviewEntity))
        {
            return NewReviewEntity.ConvertTo();
        }
        else
        {
            throw new UpdateException();
        }
    }
    public MoimReviewDTO updateofcomment(String moimId, String comment)
    {
        MoimReviewEntity NewReviewEntity = moimReviewMapper.getReviewsByMoimId(moimId).ConvertToNewcomment(comment);
        moimReviewMapper.updateofcomment(NewReviewEntity);
        MoimReviewEntity moimReviewEntity1 = moimReviewMapper.getReviewsByMoimId(moimId);
        if(moimReviewEntity1.equals(NewReviewEntity))
        {
            return NewReviewEntity.ConvertTo();
        }
        else
        {
            throw new UpdateException();
        }
    }
    public MoimReviewDTO updateofScoreAndComment(String moimId, Float score, String comment)
    {
        MoimReviewEntity NewReviewEntity = moimReviewMapper.getReviewsByMoimId(moimId).ConvertToNew(comment, score);
        moimReviewMapper.updateofcommentandscore(NewReviewEntity);
        MoimReviewEntity moimReviewEntity1 = moimReviewMapper.getReviewsByMoimId(moimId);
        if(moimReviewEntity1.equals(NewReviewEntity))
        {
            return NewReviewEntity.ConvertTo();
        }
        else
        {
            throw new UpdateException();
        }
    }
    public MoimReviewDTO Insert(MoimReviewDTO moimReviewDTO)
    {
        moimReviewMapper.InsertofmoimReview(moimReviewDTO.ConvertTo());
        MoimReviewEntity moimReviewEntity = moimReviewMapper.getReviewsByMoimId(moimReviewDTO.getMoimId());
        if(moimReviewEntity != null)
        {
            return moimReviewEntity.ConvertTo();
        }
        else
        {
            throw new InsertException();
        }
    }
    public Boolean Delete(String moimId, String reviewer)
    {
        moimReviewMapper.deleteofmoimReview(moimId, reviewer);
        MoimReviewEntity moimReviewEntity = moimReviewMapper.getReviewsByMoimId(moimId);
        if(moimReviewEntity != null)
        {
            throw new DeleteException();
        }
        else
        {
            return true;
        }
    }
}
