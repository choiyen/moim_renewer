package moim.renew.backend.domain.Moim.MoimMain;


import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")  // H2 테스트용 application-test.yml 적용
@MapperScan("moim.renew.backend.domain.Moim.MoimMain.Mapper")
@Transactional  // 테스트 후 자동 롤백
public class MoimMapperIntegrationTest
{

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private MoimMapper moimMapper;

    @Autowired
    private MoimCategoryMapper moimCategoryMapper;

    @Autowired
    private MoimCategoryDetaillMapper categoryDetaillMapper;

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
    }

    @Test
    @DisplayName("Moim CRUD 통합 테스트")
    void testMoimCRUD() {
        // INSERT
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
        assertNotNull(moim.getMoimId(), "Moim INSERT 후 ID가 생성되어야 함");

        // SELECT
        MoimEntity selected = moimMapper.SelectMoimById(moim.getMoimId());
        assertEquals("테스트 모임", selected.getTitle(), "SELECT 시 제목이 일치해야 함");

        // UPDATE
        selected.setTitle("업데이트된 모임");
        moimMapper.UpdateMoim(selected);
        MoimEntity updated = moimMapper.SelectMoimById(selected.getMoimId());
        assertEquals("업데이트된 모임", updated.getTitle(), "UPDATE 후 제목이 변경되어야 함");

        // DELETE
        moimMapper.DeleteMoim(updated.getMoimId(), "tester");
        MoimEntity deleted = moimMapper.SelectMoimById(updated.getMoimId());
        assertNull(deleted, "DELETE 후 조회 시 null이어야 함");
    }
}
