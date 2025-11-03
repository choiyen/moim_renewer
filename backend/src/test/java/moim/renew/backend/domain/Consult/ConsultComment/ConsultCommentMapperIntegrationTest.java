package moim.renew.backend.domain.Consult.ConsultComment;


import moim.renew.backend.domain.Consult.ConsultCategory.Entity.ConsultCategoryEntity;
import moim.renew.backend.domain.Consult.ConsultCategory.Mapper.ConsultCategoryMapper;
import moim.renew.backend.domain.Consult.ConsultComment.Entity.ConsultCommentEntity;
import moim.renew.backend.domain.Consult.ConsultComment.Mapper.ConsultCommentMapper;
import moim.renew.backend.domain.Consult.ConsultMain.Entity.ConsultEntity;
import moim.renew.backend.domain.Consult.ConsultMain.Mapper.ConsultMapper;
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
@MapperScan("moim.renew.backend.domain.Consult.ConsultComment.Mapper")
@Transactional
public class ConsultCommentMapperIntegrationTest
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConsultCategoryMapper consultCategoryMapper;

    @Autowired
    private ConsultMapper consultMapper;

    @Autowired
    private ConsultCommentMapper consultCommentMapper;

    @BeforeEach
    void setUpConsultComment() {
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
        consultCategoryMapper.InsertConsultCategory(new ConsultCategoryEntity(1, "test 진행"));
        ConsultEntity consultEntity = ConsultEntity.builder()
                .consultCategoryId(1)
                .moimConsultId("testConsult")
                .nickname("tester")
                .title("test 준비용 파일입니다.")
                .consultComment("test 준비 중임")
                .build();
        consultMapper.InsertConsult(consultEntity);
    }

    @Test
    @DisplayName("Consult의 댓글 기능 TEST")
    void testCategoryCRUD() {
        ConsultCommentEntity consultComment = ConsultCommentEntity
                .builder()
                .nickname("tester")
                .profileImg("dffsfdsf")
                .moimConsultId("testConsult")
                .moimConsultCommentId("testConsultComment")
                .comments("댓글 내용")
                .password("password")
                .build();

        //INSERT
        consultCommentMapper.InsertConsultComment(consultComment);

        //SELECT
        ConsultCommentEntity selected = consultCommentMapper.SelectConsultComment(consultComment.getMoimConsultCommentId());
        assertNotNull(selected, "Insert 후에는 값이 존재해야 함");
        assertEquals("댓글 내용", selected.getComments(), "조회 시에 값이 일치하는지를 TEST");

        //UPDATE
        selected.setComments("댓글변경");
        consultCommentMapper.UpdateConsultComment(selected);
        ConsultCommentEntity updated = consultCommentMapper.SelectConsultComment(selected.getMoimConsultCommentId());
        assertEquals("댓글변경", updated.getComments(), "조회 시에 값이 일치하는지를 TEST");

        //LIST 테스트
        List<ConsultCommentEntity> consultCommentEntities = consultCommentMapper.SelectConsultComments(selected.getMoimConsultId());
        assertTrue(consultCommentEntities.size() >= 0, "조회 시도 시 항상 0보다 크거나 같아야 한다. null 값인 -1이 찍여선 안됨");

        //DELETE
        consultCommentMapper.DeleteConsultComment(updated.getMoimConsultCommentId(), updated.getPassword());
        ConsultCommentEntity deleted = consultCommentMapper.SelectConsultComment(updated.getMoimConsultCommentId());
        assertNull(deleted, "이미 삭제된 데이터이므로 조회 시에 항상 null 값을 출력해야 한다.");

    }
}
