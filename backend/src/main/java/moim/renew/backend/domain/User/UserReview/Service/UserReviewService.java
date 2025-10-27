package moim.renew.backend.domain.User.UserReview.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.domain.User.UserReview.DTO.UserReviewDTO;
import moim.renew.backend.domain.User.UserReview.Entity.UserReviewEntity;
import moim.renew.backend.domain.User.UserReview.Mapper.UserReviewMapper;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserReviewService
{
    @Autowired
    UserReviewMapper userReviewMapper;

    public UserReviewDTO InsertuserReview(UserReviewDTO userReviewDTO)
    {
        UserReviewEntity  userReviewEntity = userReviewDTO.convertTo();
        userReviewMapper.InsertuserReview(userReviewEntity);
        UserReviewEntity userReviewEntity1 = userReviewMapper.FindGet(userReviewEntity.getReviewer(), userReviewEntity.getReviewee());
        if(userReviewEntity1 == null)
        {
            throw new InsertException("모임 커뮤니티 가입자에 대한 별점 생성에 실패하였습니다.");
        }
        else
        {
            return userReviewEntity1.convertTo();
        }
    }
    public UserReviewDTO UpdateuserReview(UserReviewDTO userReviewDTO)
    {
        UserReviewEntity  userReviewEntity = userReviewDTO.convertTo();
        UserReviewEntity NewReviewEntity = userReviewMapper.FindGet(userReviewDTO.getReviewer(), userReviewDTO.getReviewee()).convertToNew(userReviewEntity);

        userReviewMapper.UpdateuserReview(NewReviewEntity);
        UserReviewEntity userReviewEntity1 = userReviewMapper.FindGet(NewReviewEntity.getReviewer(), NewReviewEntity.getReviewee());
        if(!userReviewEntity1.equals(NewReviewEntity))
        {
            throw new InsertException("모임 커뮤니티 가입자에 대한 별점 변경에 실패하였습니다.");
        }
        else
        {
            return userReviewEntity1.convertTo();
        }

    }
    public Boolean DeleteuserReview(String reviewer, String reviewee)
    {
        userReviewMapper.DeleteuserReview(reviewer, reviewee);
        UserReviewEntity userReviewEntity1 = userReviewMapper.FindGet(reviewer,reviewee);
        if(userReviewEntity1 != null)
        {
            throw new InsertException("해당 유저에 대해 매겨놓은 모임 정보 삭제에 실패하였습니다.");
        }
        else
        {
            return true;
        }
    }


    public List<UserReviewDTO> FindofuserReview(String reviewer)
    {
        List<UserReviewEntity> userReviewEntities = userReviewMapper.FindofuserReview(reviewer);
        List<UserReviewDTO>userReviewDTOS = new ArrayList<>();
        for(UserReviewEntity userReviewEntity : userReviewEntities)
        {
            userReviewDTOS.add(userReviewEntity.convertTo());
        }
        return userReviewDTOS;
    }

    public UserReviewDTO FindofuserReviewId(String id)
    {
        UserReviewEntity userReviewEntity = userReviewMapper.FindofuserReviewId(id);
        if(userReviewEntity != null)
        {
            return userReviewEntity.convertTo();
        }
        else
        {
            throw new SelectException();
        }
    }
}
