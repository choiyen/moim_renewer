

package moim.renew.backend.domain.User.UserReview;


import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.domain.User.UserMain.Entity.UserEntity;
import moim.renew.backend.domain.User.UserMain.Mapper.UserMapper;
import moim.renew.backend.domain.User.UserReview.Entity.UserReviewEntity;
import moim.renew.backend.domain.User.UserReview.Mapper.UserReviewMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@MapperScan("moim.renew.backend.domain.User.UserReview.Mapper")
@Transactional
public class UserReviewMapperIntegrationTest
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MoimCategoryMapper moimCategoryMapper;

    @Autowired
    private MoimCategoryDetaillMapper moimCategoryDetaillMapper;

    @Autowired
    private UserReviewMapper userReviewMapper;

    @BeforeEach
    void setupUserReview() {
        moimCategoryMapper.InsertCategory(new MoimCateGoryEntity(1, "dfsfdfsd"));
        moimCategoryDetaillMapper.insertCategoryDetail(new MoimCategoryDetailEntity("1", 1, "dfdsdsfsdf"));
        UserEntity user = UserEntity.builder()
                .userId("tester")
                .nickname("tester")
                .password("password")
                .provider("LOCAL")
                .profileImg("dffsfdsf")
                .address("dsfsfdsfsf")
                .addressDetail("fdsfsfsf")
                .interests("1")
                .build();
        UserEntity user2 = UserEntity.builder()
                .userId("tester2")
                .nickname("tester2")
                .password("password")
                .provider("LOCAL")
                .profileImg("dffsfdsf")
                .address("dsfsfdsfsf")
                .addressDetail("fdsfsfsf")
                .interests("1")
                .build();
        userMapper.InsertUser(user);
        userMapper.InsertUser(user2);
    }

    @Test
    @DisplayName("UserReview CRUD 통합 테스트")
    void testUserReviewCRUD() {
        UserReviewEntity userReviewEntity = UserReviewEntity.builder()
                .reviewer("tester2")
                .reviewee("tester")
                .score(2.5F)
                .build();

        //INSERT TEST 코드
        userReviewMapper.InsertuserReview(userReviewEntity);
        assertEquals("tester", userReviewEntity.getReviewee(), "SELECT 시 리뷰 받는 사람 값이 존재햐야 함");
        assertEquals("tester2", userReviewEntity.getReviewer(), "SELECT 시 리뷰 주는 사람 값이 존재햐야 함");

        //SELECT TEST 코드
        UserReviewEntity userReviewEntity1 = userReviewMapper.FindGet(userReviewEntity.getReviewer(), userReviewEntity.getReviewee());
        assertEquals(2.5F, userReviewEntity1.getScore(), "SELECT 시 SCORE 값이 존재햐야 함");


        userReviewEntity.setScore(5.0F);
        userReviewMapper.UpdateuserReview(userReviewEntity);
        UserReviewEntity updated = userReviewMapper.FindGet(userReviewEntity.getReviewer(), userReviewEntity.getReviewee());
        assertEquals(5.0F, updated.getScore(), "Update 시 Score 값이 달라져야 함");

    }

}
