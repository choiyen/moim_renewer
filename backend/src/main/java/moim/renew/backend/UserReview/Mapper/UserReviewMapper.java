package moim.renew.backend.UserReview.Mapper;

import moim.renew.backend.UserReview.Entity.UserReviewEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserReviewMapper
{
    void InsertuserReview(UserReviewEntity userReviewEntity);
    void UpdateuserReview(UserReviewEntity userReviewEntity);
    void DeleteuserReview(String reviewer, String reviewee);
    List<UserReviewEntity> FindofuserReview(String reviewer);
    UserReviewEntity FindGet(String reviewer, String reviewee);
}
