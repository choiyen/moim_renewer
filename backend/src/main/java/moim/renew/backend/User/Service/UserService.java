package moim.renew.backend.User.Service;

import lombok.extern.slf4j.Slf4j;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.User.Entity.UserEntity;
import moim.renew.backend.User.Mapper.UserMapper;
import moim.renew.backend.config.Exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;

@Service
@Slf4j
public class UserService
{
    @Autowired
    private UserMapper userMapper;

    public UserDTO FindUserID(String Email)
    {
        UserEntity userEntity = userMapper.FindofUserID(Email);
        if(userEntity != null)
        {
            log.info("정상적으로 User 정보를 찾는데 성공하였습니다.");
            return userEntity.convertTo();
        }
        else
        {
            log.error("유저 정보를 찾는데 실패하였습니다. 아무래도 JWT 토큰이 만료된 것 같아요");
            throw new FindException("해당 이메일을 가진 User 정보를 찾을 수 없습니다.");
        }
    }
    public UserDTO UserIDInsert(UserDTO userDTO, PasswordEncoder passwordEncoder)
    {
        UserEntity userEntity = userDTO.convertTo().convertToPassword(passwordEncoder);
        userMapper.InsertUser(userEntity);
        UserDTO userDTO2 = FindUserID(userDTO.getUser_id());
        if(userDTO2 == null)
        {
            log.error("User 정보를 생성하는데 실패하였습니다.");

            throw new InsertException();
        }
        else
        {
            log.info("정상적으로 User 정보를 생성하는데 성공하였습니다.");
            return userDTO2;
        }
    }
    public Boolean UserDelete(String Email, String password, PasswordEncoder passwordEncoder)
    {
        userMapper.DeleteUser(Email, passwordEncoder.encode(password));
        UserEntity userEntity = userMapper.FindofUserID(Email);
        if(userEntity != null)
        {
            log.error("정상적으로 User 정보가 삭제되지 않았습니다.");
            throw new InsertException("정상적으로 User 정보가 삭제되지 않았습니다.");
        }
        else
        {
            log.info("정상적으로 User 정보가 삭제되었습니다.");
            return true;
        }
    }
    public Boolean UserPasswordChange(String Email, String Password, PasswordEncoder passwordEncoder)
    {
        userMapper.UpdatePasswordByEmail(Email, passwordEncoder.encode(Password));
        UserEntity userEntity = userMapper.FindofUserID(Email);
        if(passwordEncoder.matches(Password, userEntity.getPassword()))
        {
            throw new InsertException("정상적으로 User 정보가 삭제되지 않았습니다.");
        }
        else
        {
            return true;
        }
    }
    public UserDTO UserIDUpdate(UserDTO NewDTO, PasswordEncoder passwordEncoder)
    {
        UserDTO OldDTO = FindUserID(NewDTO.getUser_id());
        UserEntity userEntity = NewDTO.convertTo().convertToReNew(OldDTO.convertTo());
        UserEntity EncodingEntity = userEntity.convertToPassword(passwordEncoder);
        userMapper.UpdateUser(EncodingEntity);
        UserDTO RenewUserDTO = FindUserID(EncodingEntity.getUser_id());
        if(RenewUserDTO.equals(EncodingEntity.convertTo()))
        {
            return RenewUserDTO;
        }
        else
        {
            throw new InsertException();
        }
    }
    public Boolean getUserID(String id)
    {
        return userMapper.getUserID(id);
    }
}
