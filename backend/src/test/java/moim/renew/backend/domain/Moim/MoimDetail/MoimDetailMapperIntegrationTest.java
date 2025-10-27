package moim.renew.backend.domain.Moim.MoimDetail;


import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.domain.Moim.MoimDetail.Entity.MoimDetailEntity;
import moim.renew.backend.domain.Moim.MoimDetail.Mapper.MoimDetailMapper;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@MapperScan("moim.renew.backend.domain.Moim.MoimDetail.Mapper")
@Transactional
public class MoimDetailMapperIntegrationTest
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MoimMapper moimMapper;

    @Autowired
    private MoimCategoryMapper moimCategoryMapper;

    @Autowired
    private MoimCategoryDetaillMapper categoryDetaillMapper;

    @Autowired
    private MoimDetailMapper moimDetailMapper;


    @BeforeEach
    void setupCategoryDetail() {
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
        categoryDetaillMapper.insertCategoryDetail(new MoimCategoryDetailEntity("1", 1, "dfdsdsfsdf"));

        MoimEntity moim = MoimEntity.builder()
                .moimId("fdsfsdfdssfsaf")
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
    }

    @Test
    @DisplayName("Moim 세부정보 CRUD 통합 test")
    void testMoimDetailCRUD() {

        MoimDetailEntity moimDetailEntity = MoimDetailEntity.builder()
                .moimId("fdsfsdfdssfsaf")
                .approval(true)
                .content("TEST 파일에 접속하셨습니다.")
                .Pay(2500.0)
                .minPeople(5)
                .build();

        moimDetailMapper.moimofInsert(moimDetailEntity);
        assertNotNull(moimDetailEntity.getMoimId(), "Moim INSERT 후 ID가 생성되어야 함");

        //SELECT
        MoimDetailEntity selected = moimDetailMapper.selectmoimdetailbyResult(moimDetailEntity.getMoimId());
        assertEquals("TEST 파일에 접속하셨습니다.", selected.getContent(), "SELECT 시 제목이 일치해야 함");

        //UPDATE
        selected.setContent("업데이트 된 모임 세부데이터");
        selected.setApproval(false);
        selected.setMinPeople(2);
        moimDetailMapper.updatemoimdetailbyResult(selected);
        MoimDetailEntity updated = moimDetailMapper.selectmoimdetailbyResult(moimDetailEntity.getMoimId());
        assertEquals("업데이트 된 모임 세부데이터", updated.getContent(), "UPDATE 후 제목이 변경되어야 함");
        assertEquals(false, updated.getApproval(), "UPDATE 후 인증 기능이 변경되어야 함");
        assertEquals(2, updated.getMinPeople(), "update 시 변경 가능한 값이 변경되어야 함 ");


        moimDetailMapper.updateByApproval(true, updated.getMoimId());
        MoimDetailEntity updated2 = moimDetailMapper.selectmoimdetailbyResult(moimDetailEntity.getMoimId());
        assertEquals(true, updated2.getApproval(), "UPDATE 후 인증 기능이 변경되어야 함");


        moimDetailMapper.deletemoimdetailbyResult(updated.getMoimId());
        MoimDetailEntity deleted = moimDetailMapper.selectmoimdetailbyResult(updated.getMoimId());
        assertNull(deleted, "DELETE 후 조회 시 값이 조회되지 않아야 함");
    }

}
