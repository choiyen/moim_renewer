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
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

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
    // 로그인
    public UserEntity getByCredentials(String email, String password, PasswordEncoder passwordEncoder) {
        UserEntity originalUser = userMapper.FindofUserID(email);
        if(originalUser != null && passwordEncoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }
    public UserDTO UserCreate(Map<String, Object> userInfo, String oauthType) {
        // 1. userInfo에서 이메일, 이름 등 추출
        String email;
        String name;
        String socialId;

        switch(oauthType.toLowerCase())
        {
            case "kakao":
                Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
                email = (String) kakaoAccount.get("email");
                Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");  // 여기서 캐스팅
                name = (String) profile.get("nickname");
                socialId = String.valueOf(userInfo.get("id"));
            case "naver":
                Map<String, Object> naverResp = (Map<String, Object>) userInfo.get("response");
                email = (String) naverResp.get("email");
                name = (String) naverResp.get("name");
                socialId = (String) naverResp.get("id");
                break;
            case "google":
                email = (String) userInfo.get("email");
                name = (String) userInfo.get("name");
                socialId = (String) userInfo.get("sub");
                break;
            case "facebook":
                email = (String) userInfo.get("email");
                name = (String) userInfo.get("nasome");
                socialId = (String) userInfo.get("id");
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 OAuth 타입입니다.");
        }

        // 2. 기존 회원 여부 조회
        UserEntity existingUser = userMapper.FindofUserID(email);
        if (existingUser != null && existingUser.getProvider().equals(oauthType.toLowerCase())) {
            // 이미 가입된 사용자라면 그대로 반환
            return existingUser.convertTo();
        }

        // 3. 신규 회원 생성
        UserEntity newUser = UserEntity.builder()
                .user_id(email)
                .Intro(null)
                .provider(socialId)
                .nickname(name)
                .review(null)
                .password(generateRandomPassword())
                .build();

        userMapper.InsertUser(newUser);

        UserEntity userEntity = userMapper.FindofUserID(email);
        if(userEntity != null)
        {
            return userEntity.convertTo();
        }
        else
        {
            throw new InsertException("OAuth2 정보는 가져왔으나, 회원가입 정보를 DB에 등록하지 못했습니다.");
        }

    }

    private String generateRandomPassword()
    {
        // 랜덤 문자열 생성 로직 (예: UUID 등)
        return UUID.randomUUID().toString();
    }
}
