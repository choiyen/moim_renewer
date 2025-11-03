package moim.renew.backend.domain.Moim.MoimCategory;


import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.domain.Moim.MoimDetail.Entity.MoimDetailEntity;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@MapperScan("moim.renew.backend.domain.Moim.MoimCategory.Mapper")
@Transactional
public class MoimCategoryMapperIntegrationTest
{
    @Autowired
    private MoimCategoryMapper moimCategoryMapper;

    @Autowired
    private MoimCategoryDetaillMapper moimCategoryDetaillMapper;

    @Autowired
    private UserMapper userMapper;

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
    }

    @Test
    @DisplayName("MoimCategory 정보 CRUD 통합 TEST")
    void testMoimCategoryCRUD() {
        MoimCateGoryEntity moimCateGoryEntity = MoimCateGoryEntity.builder()
                .categoryId(2)
                .categorisation("TEST 파일에 접속하셨습니다.")
                .build();

        //INSERT
        moimCategoryMapper.InsertCategory(moimCateGoryEntity);
        assertNotNull(moimCateGoryEntity.getCategoryId(), "Moim INSERT 후 ID가 생성되어야 함");

        //SELECT
        MoimCateGoryEntity selected = moimCategoryMapper.GetByCategoryId(moimCateGoryEntity.getCategoryId());
        assertEquals("TEST 파일에 접속하셨습니다.", selected.getCategorisation(), "SELECT 시 제목이 일치해야 함");

        //UPDATE
        selected.setCategorisation("업데이트 된 모임 카테고리");
        moimCategoryMapper.UpdateCategory(selected);
        MoimCateGoryEntity updated = moimCategoryMapper.GetByCategoryId(selected.getCategoryId());
        assertEquals("업데이트 된 모임 카테고리", updated.getCategorisation(), "UPDATE 후 제목이 변경되어야 함");

        //LIST 테스트
        List<MoimCateGoryEntity> moimCateGoryEntityList = moimCategoryMapper.SelectCategory();
        assertTrue(moimCateGoryEntityList.size() > 0, "카테고리 목록이 조회되어야 함");

        moimCategoryMapper.DeleteCategory(updated.getCategoryId());
        MoimCateGoryEntity deleted = moimCategoryMapper.GetByCategoryId(updated.getCategoryId());
        assertNull(deleted, "DELETE 후 조회 시 값이 조회되지 않아야 함");
    }

    @Test
    @DisplayName("Moim 세부 카테고리 CRUD 통합 test")
    void testMoimCategoryDetailCRUD() {
        MoimCateGoryEntity moimCateGoryEntity = MoimCateGoryEntity.builder()
                .categoryId(2)
                .categorisation("TEST 파일에 접속하셨습니다.")
                .build();

        //INSERT
        moimCategoryMapper.InsertCategory(moimCateGoryEntity);
        assertNotNull(moimCateGoryEntity.getCategoryId(), "Moim INSERT 후 ID가 생성되어야 함");

        //SELECT
        MoimCateGoryEntity selected = moimCategoryMapper.GetByCategoryId(moimCateGoryEntity.getCategoryId());
        assertEquals("TEST 파일에 접속하셨습니다.", selected.getCategorisation(), "SELECT 시 제목이 일치해야 함");

        MoimCategoryDetailEntity moimCategoryDetailEntity = MoimCategoryDetailEntity.builder()
                .categoryId(2)
                .categoryDetailId("dffsaff")
                .categorisationDetail("TEST 파일에 접속하셨습니다. - 세부 테스트")
                .build();

        //INSERT
        moimCategoryDetaillMapper.insertCategoryDetail(moimCategoryDetailEntity);
        assertNotNull(moimCategoryDetailEntity.getCategoryDetailId(), "Moim INSERT 후 ID가 생성되어야 함");

        //SELECT
        MoimCategoryDetailEntity selected2 = moimCategoryDetaillMapper.selectCategoryDetailById(moimCategoryDetailEntity.getCategoryDetailId());
        assertEquals("TEST 파일에 접속하셨습니다. - 세부 테스트", selected2.getCategorisationDetail(), "SELECT 시 제목이 일치해야 함");

        //UPDATE
        selected2.setCategorisationDetail("업데이트 된 모임 세부카테고리");
        moimCategoryDetaillMapper.updateCategoryDetail(selected2);
        MoimCategoryDetailEntity updated = moimCategoryDetaillMapper.selectCategoryDetailById(selected2.getCategoryDetailId());
        assertEquals("업데이트 된 모임 세부카테고리", selected2.getCategorisationDetail(), "SELECT 시 제목이 일치해야 함");

        //LIST SELECT
        List<MoimCategoryDetailEntity> moimCategoryDetailEntities = moimCategoryDetaillMapper.selectCategoryDetailByCategoryId(selected2.getCategoryId());
        assertTrue(moimCategoryDetailEntities.size() > 0, "카테고리 목록이 조회되어야 함");
    }

}
