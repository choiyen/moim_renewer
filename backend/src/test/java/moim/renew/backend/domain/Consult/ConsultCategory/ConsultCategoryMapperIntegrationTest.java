package moim.renew.backend.domain.Consult.ConsultCategory;


import moim.renew.backend.domain.Consult.ConsultCategory.Entity.ConsultCategoryEntity;
import moim.renew.backend.domain.Consult.ConsultCategory.Mapper.ConsultCategoryMapper;
import moim.renew.backend.domain.Consult.ConsultMain.Mapper.ConsultMapper;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.User.UserMain.Entity.UserEntity;
import moim.renew.backend.domain.User.UserMain.Mapper.UserMapper;
import moim.renew.backend.gobal.config.Enum.UserTypeEnum;
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
@MapperScan("moim.renew.backend.domain.Consult.ConsultCategory.Mapper")
@Transactional
public class ConsultCategoryMapperIntegrationTest
{
    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setupCategoryDetail() {
        UserEntity user = UserEntity.builder()
                .userId("tester")
                .type(String.valueOf(UserTypeEnum.MANAGER))
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
    @DisplayName("ConsultCategory 정보 CRUD 통합 TEST")
    void testConsultCategory() {
        ConsultCategoryEntity consultCategoryEntity = ConsultCategoryEntity.of(1, "업데이트 된 상담 카테고리");

        //INSERT
        consultCategoryMapper.InsertConsultCategory(consultCategoryEntity);
        assertNotNull(consultCategoryEntity.getConsultCategoryId());

        //SELECT
        ConsultCategoryEntity selected = consultCategoryMapper.SelectConsultCategoryId(consultCategoryEntity.getConsultCategoryId());
        assertEquals("업데이트 된 상담 카테고리", selected.getConsultType(), "SELECT 시 출력되는 상담 카테고리");

        //SELECT2
        List<ConsultCategoryEntity> consultCategoryEntities = consultCategoryMapper.SelectConsultCategory();
        assertTrue(consultCategoryEntities.size() > 0, "카테고리 목록이 조회되어야 함");

        //UPDATE
        selected.setConsultType("업데이트 완료된 상담 카테고리");
        consultCategoryMapper.UpdateConsultCategory(selected);
        ConsultCategoryEntity updated = consultCategoryMapper.SelectConsultCategoryId(selected.getConsultCategoryId());
        assertEquals("업데이트 완료된 상담 카테고리", updated.getConsultType(), "UPDATE 후 제목이 변경되어야 함");


        //DELETE
        consultCategoryMapper.DeleteConsultCategory(updated.getConsultCategoryId());
        ConsultCategoryEntity deleted = consultCategoryMapper.SelectConsultCategoryId(updated.getConsultCategoryId());
        assertNull(deleted, "DELETE 후 조회 시 값이 조회되지 않아야 한다.");

    }

}
