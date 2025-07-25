package moim.renew.backend.User.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.User.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService
{
    @Autowired
    private UserMapper userMapper;

    public UserDTO FindUserID(String Email)
    {

    }


    public Boolean getUserID(String id)
    {
        return userMapper.getUserID(id);
    }
}
