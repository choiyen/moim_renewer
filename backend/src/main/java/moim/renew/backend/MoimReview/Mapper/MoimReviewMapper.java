package moim.renew.backend.MoimReview.Mapper;

import moim.renew.backend.MoimReview.Entity.MoimReviewEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoimReviewMapper
{
  void InsertofmoimReview(MoimReviewEntity moimReviewEntity);
  void updateofscore(MoimReviewEntity moimReviewEntity);
  void updateofcomment(MoimReviewEntity moimReviewEntity);
  void updateofcommentandscore(MoimReviewEntity moimReviewEntity);
  void deleteofmoimReview(String moimId, String reviewer);
  double getAverageScoreByMoimId(String moimId);
  MoimReviewEntity getReviewsByMoimId(String moimId);
}
