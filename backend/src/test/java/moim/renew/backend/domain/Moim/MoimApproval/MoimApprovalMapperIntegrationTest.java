package moim.renew.backend.domain.Moim.MoimApproval;


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
import moim.renew.backend.domain.User.UserMain.Entity.UserEntity;
import moim.renew.backend.domain.User.UserMain.Mapper.UserMapper;
import moim.renew.backend.gobal.config.Enum.ApprovalStatus;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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
@MapperScan("moim.renew.backend.domain.Moim.MoimApproval.Mapper")
@Transactional
public class MoimApprovalMapperIntegrationTest
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
    private ApprovalMapper approvalMapper;

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
    @DisplayName("MoimApproval에 관한 CRUD 통합 TEST 입니다.")
    void testMoimApprovalCRUD() {
        ApprovalEntity approval = ApprovalEntity.builder()
                .userId("tester")
                .moimId("moimtest1")
                .status(String.valueOf(ApprovalStatus.APPROVED))
                .build();

        //Insert
        approvalMapper.insertMoimApproval(approval);
        assertNotNull(approval.getMoimId(), "데이터가 정상적으로 존재하여야 한다.");
        //SELECT
        List<ApprovalEntity> selected = approvalMapper.selectMoimApprovalsByMoimId(approval.getMoimId());
        assertTrue(selected.size() >= 0, "데이터 값은 항상 0보다 크거나 같아야 한다.");
        //SELECT2
        ApprovalEntity selected2 = approvalMapper.selectMoimApprovalsByUserId(approval.getUserId(), approval.getMoimId());
        assertEquals(String.valueOf(ApprovalStatus.APPROVED), selected2.getStatus(), "현재 조회 등급은 다음과 같다.");

        //UPDATE
        approvalMapper.updateMoimApprovalStatus(selected2.getApprovalId(), String.valueOf(ApprovalStatus.CANCELLED));
        ApprovalEntity updated = approvalMapper.selectMoimApprovalsByUserId(selected2.getUserId(), selected2.getMoimId());
        assertEquals(String.valueOf(ApprovalStatus.CANCELLED), updated.getStatus(), "업데이트 정보가 정상적으로 반영되어야 함");

        //DELETE
        approvalMapper.deleteMoimApproval(updated.getApprovalId());
        ApprovalEntity deleted = approvalMapper.selectMoimApprovalsByUserId(updated.getUserId(), updated.getMoimId());
        assertNull(deleted, "데이터가 조회되지 않아야 정상임");

    }
}

