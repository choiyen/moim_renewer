package moim.renew.backend.domain.User.UserMain;


import moim.renew.backend.domain.Moim.MoimCategory.Category.Entity.MoimCateGoryEntity;
import moim.renew.backend.domain.Moim.MoimCategory.Category.Mapper.MoimCategoryMapper;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Mapper.MoimCategoryDetaillMapper;
import moim.renew.backend.domain.User.UserMain.Entity.UserEntity;
import moim.renew.backend.domain.User.UserMain.Mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@MapperScan("moim.renew.backend.domain.User.UserMain.Mapper")
@Transactional
public class UserMapperIntegrationTest
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MoimCategoryMapper moimCategoryMapper;

    @Autowired
    private MoimCategoryDetaillMapper moimCategoryDetaillMapper;

    @BeforeEach
    void setupCategoryDetail() {
        moimCategoryMapper.InsertCategory(new MoimCateGoryEntity(1, "dfsfdfsd"));
        moimCategoryDetaillMapper.insertCategoryDetail(new MoimCategoryDetailEntity("1", 1, "dfdsdsfsdf"));
    }


    @Test
    @DisplayName("User CRUD 통합 테스트")
    void testUserCRUD() {
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


       //INSERT TEST 코드
       userMapper.InsertUser(user);
       assertNotNull(user.getUserId(), "User INSERT 후 ID 값이 생성되었습니다.");


       //SELECT TEST 코드
        UserEntity userEntity = userMapper.FindUserID(user.getUserId());
        assertEquals("tester", userEntity.getNickname(), "SELECT 시 nickname 값이 존재햐야 함");

        //UPDATE
        userEntity.setNickname("업데이트 된 닉네임");
        userMapper.UpdateUser(userEntity);
        UserEntity updated = userMapper.FindUserID(userEntity.getUserId());
        assertEquals("업데이트 된 닉네임", updated.getNickname(), "SELECT 시 nickname 값이 존재햐야 함");


        //DELETE
        userMapper.DeleteUser(updated.getUserId(), updated.getPassword());
        UserEntity deleted = userMapper.FindUserID(updated.getUserId());
        assertNull(deleted, "DELETE 후 조회 시 null이어야 함");
    }
}
