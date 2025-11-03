package moim.renew.backend.domain.Moim.MoimReview.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.Moim.MoimReview.DTO.MoimReviewDTO;
import moim.renew.backend.domain.Moim.MoimReview.Entity.MoimReviewEntity;
import moim.renew.backend.domain.Moim.MoimReview.Mapper.MoimReviewMapper;
import moim.renew.backend.domain.User.UserMain.DTO.UserDTO;
import moim.renew.backend.domain.User.UserMain.Entity.UserEntity;
import moim.renew.backend.domain.User.UserMain.Mapper.UserMapper;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.SelectException;
import moim.renew.backend.gobal.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MoimReviewService
{
    @Autowired
    private MoimReviewMapper moimReviewMapper;

    @Autowired
    private UserMapper userMapper;


    public MoimReviewDTO getReviewByMoimId(String moimId, String userId) {
        UserEntity user = userMapper.FindUserID(userId);
        if(user != null)
        {
            MoimReviewEntity moimReviewEntity = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname());
            if (moimReviewEntity == null)
            {
                throw new SelectException();
            }
            else
            {
                return moimReviewEntity.ConvertTo();
            }
        }
        else
        {
            throw new SelectException();
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


    public MoimReviewDTO updateofScore(String moimId, String userId, Float score)
    {
        UserEntity user = userMapper.FindUserID(userId);
        if(user != null)
        {
            MoimReviewEntity NewReviewEntity = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname()).ConvertToNewScore(score);
            moimReviewMapper.updateofscore(NewReviewEntity);
            MoimReviewEntity moimReviewEntity1 = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname());
            if(moimReviewEntity1.equals(NewReviewEntity))
            {
                return NewReviewEntity.ConvertTo();
            }
            else
            {
                throw new UpdateException();
            }
        }
        else
        {
            throw new SelectException();
        }

    }
    public MoimReviewDTO updateofcomment(String moimId, String userId, String comment)
    {
        UserEntity user = userMapper.FindUserID(userId);
        if(user != null)
        {
            MoimReviewEntity NewReviewEntity = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname()).ConvertToNewcomment(comment);
            moimReviewMapper.updateofcomment(NewReviewEntity);
            MoimReviewEntity moimReviewEntity1 = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname());
            if(moimReviewEntity1.equals(NewReviewEntity))
            {
                return NewReviewEntity.ConvertTo();
            }
            else
            {
                throw new UpdateException();
            }
        }
        else
        {
            throw new SelectException();
        }

    }
    public MoimReviewDTO updateofScoreAndComment(String moimId, String userId, Float score, String comment)
    {
        UserEntity user = userMapper.FindUserID(userId);
        if(user != null)
        {
            MoimReviewEntity NewReviewEntity = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname()).ConvertToNew(comment, score);
            moimReviewMapper.updateofcommentandscore(NewReviewEntity);
            MoimReviewEntity updated = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, user.getNickname());

            if(updated.equals(NewReviewEntity))
            {
                return NewReviewEntity.ConvertTo();
            }
            else
            {
                throw new UpdateException();
            }
        }
        else
        {
            throw new SelectException();
        }

    }
    public MoimReviewDTO Insert(MoimReviewDTO moimReviewDTO)
    {
        moimReviewMapper.InsertofmoimReview(moimReviewDTO.ConvertTo());
        MoimReviewEntity moimReviewEntity = moimReviewMapper.getReviewsBynicknameAndMoimId(moimReviewDTO.getMoimId(), moimReviewDTO.getReviewer());
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
        MoimReviewEntity moimReviewEntity = moimReviewMapper.getReviewsBynicknameAndMoimId(moimId, reviewer);
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
