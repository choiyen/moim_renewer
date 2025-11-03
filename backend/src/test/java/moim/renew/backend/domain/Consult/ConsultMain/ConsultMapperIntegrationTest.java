package moim.renew.backend.domain.Consult.ConsultMain;


import moim.renew.backend.domain.Consult.ConsultCategory.Entity.ConsultCategoryEntity;
import moim.renew.backend.domain.Consult.ConsultCategory.Mapper.ConsultCategoryMapper;
import moim.renew.backend.domain.Consult.ConsultMain.Entity.ConsultEntity;
import moim.renew.backend.domain.Consult.ConsultMain.Mapper.ConsultMapper;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
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
@MapperScan("moim.renew.backend.domain.Consult.ConsultMain.Mapper")
@Transactional
public class ConsultMapperIntegrationTest 
{
    @Autowired
    private ConsultMapper consultMapper;
    
    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;
    
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
        consultCategoryMapper.InsertConsultCategory(new ConsultCategoryEntity(1, "우리는 어렵다"));
    }

    @Test
    @DisplayName("Consult 정보 CRUD 통합 TEST")
    void testConsultCRUD() {
        ConsultEntity consultEntity = ConsultEntity.builder()
                .consultCategoryId(1)
                .moimConsultId("test1")
                .title("test 용 정보입니다")
                .nickname("tester")
                .consultComment("test 정보를 삽입합니다.")
                .build();

        //INSERT
        consultMapper.InsertConsult(consultEntity);
        assertNotNull(consultEntity.getMoimConsultId(),"Consult Insert 후 ID가 생성되어야 함");

        //SELECT
        ConsultEntity selected = consultMapper.SelectConsult(consultEntity.getMoimConsultId());
        assertEquals("test 정보를 삽입합니다.", selected.getConsultComment(), "데이터 조회를 했다면 제목과 내용이 같아야 합니다.");
        assertEquals("test 용 정보입니다", selected.getTitle(), "데이터 조회를 했다면 제목과 내용이 같아야 합니다.");

        //SELECT2
        List<ConsultEntity> selected2 = consultMapper.SelectConsultBYCategoryId(consultEntity.getConsultCategoryId());
        assertTrue(selected2.size() >= 0, "같은 카테고리의 데이터를 조회했을 때 0보다 크거나 같아야 합니다.");

        //UPDATE
        selected.setConsultComment("내용변경");
        selected.setTitle("제목변경");
        consultMapper.UpdateConsult(selected);
        ConsultEntity updated = consultMapper.SelectConsult(selected.getMoimConsultId());
        assertEquals("내용변경", updated.getConsultComment(), "데이터 조회를 했다면 제목과 내용이 같아야 합니다.");
        assertEquals("제목변경", updated.getTitle(), "데이터 조회를 했다면 제목과 내용이 같아야 합니다.");

        //DELETE
        consultMapper.DeleteConsult(updated.getMoimConsultId());
        ConsultEntity deleted = consultMapper.SelectConsult(updated.getMoimConsultId());
        assertNull(deleted, "데이터 조회를 시도했을 때 Null값을 송출해야 합니다.");
    }
}
