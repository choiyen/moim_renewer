package moim.renew.backend.User.Mapper;

import moim.renew.backend.User.Entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean getUserID(String UserID);
    void UpdatePasswordByEmail(String email, String password);
    String FindofUserID(String email);
    void DeleteUser(String user_id, String password);
    void UpdateUser(UserEntity userEntity);
    void InsertUser(UserEntity userEntity);
}
