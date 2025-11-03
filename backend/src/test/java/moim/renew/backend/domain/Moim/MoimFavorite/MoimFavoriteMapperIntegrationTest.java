package moim.renew.backend.domain.Moim.MoimFavorite;

import moim.renew.backend.domain.Moim.MoimApproval.Mapper.ApprovalMapper;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.domain.Moim.MoimDetail.Entity.MoimDetailEntity;
import moim.renew.backend.domain.Moim.MoimDetail.Mapper.MoimDetailMapper;
import moim.renew.backend.domain.Moim.MoimFavorite.Entity.FavoriteEntity;
import moim.renew.backend.domain.Moim.MoimFavorite.Mapper.FavoriteMapper;
import moim.renew.backend.domain.Moim.MoimMain.Entity.MoimEntity;
import moim.renew.backend.domain.Moim.MoimMain.Mapper.MoimMapper;
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
@MapperScan("moim.renew.backend.domain.Moim.MoimFavorite.Mapper")
@Transactional
public class MoimFavoriteMapperIntegrationTest
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
    private FavoriteMapper favoriteMapper;

    @BeforeEach
    void setupFavorite() {
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
        userMapper.InsertUser(user);
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
    @DisplayName("MoimFavorite에 관한 CRUD 통합 TEST")
    void testMoimFavoriteCRUD ()
    {
        FavoriteEntity favoriteEntity = FavoriteEntity.builder()
                .moimId("moimtest1")
                .userId("tester")
                .build();

        //INSERT
        favoriteMapper.Insertfavoriteofmoim(favoriteEntity);
        //SELECT
        FavoriteEntity selected = favoriteMapper.selectofmoimId(favoriteEntity.getMoimId(), favoriteEntity.getUserId());
        assertNotNull(selected, "데이터가 정상적으로 존재하여야 한다.");

        assertEquals(favoriteEntity.getMoimId(), selected.getMoimId(), "데이터 값이 일치해야 함");
        assertEquals(favoriteEntity.getUserId(), selected.getUserId(), "데이터 값이 일치해야 함");

        //SELECT2
        List<FavoriteEntity> selectedList = favoriteMapper.selectoffavorite(favoriteEntity.getUserId());
        assertTrue(selectedList.size() >= 0, "리스트 값이 null이 아니어야 한다.");

        //DELETE
        favoriteMapper.Deleteoffavorite(selected.getMoimId(), selected.getUserId());
        FavoriteEntity deleted = favoriteMapper.selectofmoimId(selected.getMoimId(), selected.getUserId());
        assertNull(deleted, "데이터가 조회되지 않아야 정상이다.");
    }
}
