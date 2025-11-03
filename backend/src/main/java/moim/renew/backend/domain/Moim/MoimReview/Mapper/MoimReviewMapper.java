package moim.renew.backend.domain.Moim.MoimReview.Mapper;

import moim.renew.backend.domain.Moim.MoimReview.Entity.MoimReviewEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoimReviewMapper
{
  void InsertofmoimReview(MoimReviewEntity moimReviewEntity);
  void updateofscore(MoimReviewEntity moimReviewEntity);
  void updateofcomment(MoimReviewEntity moimReviewEntity);
  void updateofcommentandscore(MoimReviewEntity moimReviewEntity);
  void deleteofmoimReview(String moimId, String reviewer);
  double getAverageScoreByMoimId(String moimId);
  List<MoimReviewEntity> getReviewsByMoimId(String moimId);
  List<MoimReviewEntity> getReviewsBynickname(String reviewer);
  MoimReviewEntity getReviewsBynicknameAndMoimId(String moimId, String reviewer);
}
