package moim.renew.backend.UserReview.Mapper;

import moim.renew.backend.UserReview.Entity.UserReviewEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserReviewMapper
{
    void InsertuserReview(UserReviewEntity userReviewEntity);
    void UpdateuserReview(UserReviewEntity userReviewEntity);
    void DeleteuserReview(String reviewer, String reviewee);
    void FindofuserReview(String reviewee);
}
