package moim.renew.backend.domain.Moim.MoimReview;

import moim.renew.backend.domain.Moim.MoimApproval.Entity.ApprovalEntity;
import moim.renew.backend.domain.Moim.MoimApproval.Mapper.ApprovalMapper;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.domain.Moim.MoimDetail.Entity.MoimDetailEntity;
import moim.renew.backend.domain.Moim.MoimDetail.Mapper.MoimDetailMapper;
import moim.renew.backend.domain.Moim.MoimMain.Entity.MoimEntity;
import moim.renew.backend.domain.Moim.MoimMain.Mapper.MoimMapper;
import moim.renew.backend.domain.Moim.MoimReview.Entity.MoimReviewEntity;
import moim.renew.backend.domain.Moim.MoimReview.Mapper.MoimReviewMapper;
import moim.renew.backend.domain.User.UserMain.Entity.UserEntity;
import moim.renew.backend.domain.User.UserMain.Mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@MapperScan("moim.renew.backend.domain.Moim.MoimReview.Mapper")
@Transactional
public class MoimReviewMapperIntegrationTest
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MoimCategoryMapper moimCategoryMapper;

    @Autowired
    private MoimCategoryDetaillMapper moimCategoryDetaillMapper;

    @Autowired
    private MoimMapper moimMapper;

    @Autowired
    private MoimDetailMapper moimDetailMapper;

    @Autowired
    private MoimReviewMapper moimReviewMapper;

    @BeforeEach
    void setupApproval() {
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
        moimCategoryMapper.InsertCategory(new MoimCateGoryEntity(1, "dfsfdfsd"));
        moimCategoryDetaillMapper.insertCategoryDetail(new MoimCategoryDetailEntity("1", 1, "dfdsdsfsdf"));
        MoimEntity moim = MoimEntity.builder()
                .moimId("moimtest1")
                .title("테스트 모임")
                .isOnline(true)
                .maxPeople(10)
                .expirationDate(new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000))
                .evenDate(new Date(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000))
                .location("서울")
                .organizer("tester")
                .description("테스트 모임 설명")
                .tag(new String[]{"여행", "스터디"})
                .categoryDetailId("1")
                .build();
        moimMapper.InsertMoim(moim);
        MoimDetailEntity moimDetailEntity = MoimDetailEntity.builder()
                .moimId("moimtest1")
                .approval(true)
                .content("TEST 파일에 접속하셨습니다.")
                .Pay(2500.0)
                .minPeople(5)
                .build();

        moimDetailMapper.moimofInsert(moimDetailEntity);
    }

    @Test
    @DisplayName("MoimReview에 관한 CRUD 통합 TEST")
    void testMoimReviewCRUD() {
        MoimReviewEntity moimReviewEntity = MoimReviewEntity.builder()
                .moimReviewId("ddfsfffs")
                .moimId("moimtest1")
                .reviewer("tester2")
                .comment("정말 복잡한 생각이 드는 리뷰네요")
                .score(4.5F)
                .build();
        //INSERT
        moimReviewMapper.InsertofmoimReview(moimReviewEntity);

        //SELECT
        MoimReviewEntity selected = moimReviewMapper.getReviewsBynicknameAndMoimId(moimReviewEntity.getMoimId(), moimReviewEntity.getReviewer());
        assertNotNull(selected, "데이터가 정상적으로 조회되어짐");
        assertEquals("정말 복잡한 생각이 드는 리뷰네요", selected.getComment(), "정상적으로 동일한 데이터가 조회됨");

        //SELECT2
        List<MoimReviewEntity> UserMoimReviewList = moimReviewMapper.getReviewsBynickname(selected.getReviewer());
        assertTrue(UserMoimReviewList.size() >= 0, "정상적으로 데이터가 조회되어 null 값이 아님");


        //SELECT3
        List<MoimReviewEntity> MoimReviewList = moimReviewMapper.getReviewsByMoimId(selected.getMoimId());
        assertTrue(UserMoimReviewList.size() >= 0, "정상적으로 데이터가 조회되어 null 값이 아님");

        //UPDATE1
        selected.setComment("데이터 변경함");
        moimReviewMapper.updateofcomment(selected);
        MoimReviewEntity updated1 = moimReviewMapper.getReviewsBynicknameAndMoimId(selected.getMoimId(), selected.getReviewer());
        assertEquals("데이터 변경함", updated1.getComment(), "정상적으로 동일한 데이터가 조회됨");

        //UPDATE2
        selected.setScore(2.0F);
        moimReviewMapper.updateofscore(selected);
        MoimReviewEntity updated2 = moimReviewMapper.getReviewsBynicknameAndMoimId(selected.getMoimId(), selected.getReviewer());
        assertEquals(2.0F, updated2.getScore(), "정상적으로 동일한 데이터가 조회됨");

        //UPDATE3
        selected.setComment("데이터 변경함2");
        selected.setScore(1.0F);
        moimReviewMapper.updateofcommentandscore(selected);
        MoimReviewEntity updated3 = moimReviewMapper.getReviewsBynicknameAndMoimId(selected.getMoimId(), selected.getReviewer());
        assertEquals("데이터 변경함2", updated3.getComment(), "정상적으로 동일한 데이터가 조회됨");
        assertEquals(1.0F, updated3.getScore(), "정상적으로 동일한 데이터가 조회됨");

        //DELETE
        moimReviewMapper.deleteofmoimReview(selected.getMoimId(), selected.getReviewer());
        MoimReviewEntity deleted = moimReviewMapper.getReviewsBynicknameAndMoimId(selected.getMoimId(), selected.getReviewer());
        assertNull(deleted, "데이터가 조회되지 않아야 한다.");
    }
}
