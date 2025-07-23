package moim.renew.backend.User.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    boolean getUserID(String UserID);

}
