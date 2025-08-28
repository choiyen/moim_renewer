package moim.renew.backend.User.Mapper;

import moim.renew.backend.User.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int getUserID(@Param("userId") String userId);

    int getNickname(@Param("nickname") String nickname);


    void UpdatePasswordByEmail(@Param("email") String email, @Param("password") String password);

    UserEntity FindUserID(@Param("userId") String userId);

    void DeleteUser(@Param("userId") String userId, @Param("password") String password);

    void UpdateUser(UserEntity userEntity);

    void InsertUser(UserEntity userEntity);
}
