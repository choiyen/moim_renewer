package moim.renew.backend.User.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moim.renew.backend.S3Service.S3ImageService;
import moim.renew.backend.Security.TokenProvider;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.User.DTO.UserTokenDTO;
import moim.renew.backend.User.Entity.UserEntity;
import moim.renew.backend.User.Service.UserService;
import moim.renew.backend.config.DTO.ResponseDTO;
import moim.renew.backend.config.Exception.InsertException;
import moim.renew.backend.config.Exception.SelectException;
import moim.renew.backend.config.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController
{
    @Autowired
    UserService userService;

    private final ResponseDTO responseDTO = new ResponseDTO();

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenProvider tokenProvider;

    private final S3ImageService awsS3Service;


    // 회원가입 설정 정상동작
    @PostMapping
    public ResponseEntity<?> userPost(
            @Valid @RequestPart("userDTO") UserDTO userDTO,   // JSON 문자열 → DTO
            @RequestPart("file") MultipartFile file   // 업로드 파일
    )     {
        try
        {
            if(file == null)
            {
                throw new SelectException("업로드 파일이 설정되어 있지 않습니다.");
            }


            String url = awsS3Service.upload(file);
            UserDTO userDTO2 = userDTO.convertToUrl(url);
            UserDTO userDTO1 = userService.UserIDInsert(userDTO2, passwordEncoder);
            if (userDTO1 != null)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "회원가입 완료", Collections.singletonList(userDTO1)));
            }
            else
            {
                throw new InsertException();
            }
        }
        catch (DuplicateKeyException e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", "닉네임이나 이메일에 중복이 존재합니다"));

        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PutMapping
    public ResponseEntity<?> UserPut(@AuthenticationPrincipal String userId,  @RequestPart("userDTO") UserDTO userDTO,   // JSON 문자열 → DTO
                                     @RequestPart("file") MultipartFile file)   // 업로드 파일)
    {
        try
        {
            if(userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인 관련 에러 발생 : JWT 토큰이 확인되지 않음");
            }
            String url = awsS3Service.upload(file);
            UserDTO userUrlDTO = userDTO.convertToUrl(url);
            UserDTO userDTO1 = userService.UserIDUpdate(userUrlDTO, passwordEncoder);
            if (userDTO1 != null)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", responseDTO.getMessage()));
            }
            else
            {
                throw new InsertException();
            }

        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 에러" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> UserGet(@AuthenticationPrincipal String userId)
    {
        try
        {
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("로그인 관련 에러 발생 : JWT 토큰이 확인되지 않음");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if (userDTO != null)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", responseDTO.getMessage()));
            }
            else
            {
                throw new SelectException();
            }
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 에러" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PutMapping("/change/password")
    public ResponseEntity<?> UserPutPassword(@AuthenticationPrincipal String userId, @RequestParam String Password)
    {
        try
        {
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("로그인 관련 에러 발생 : JWT 토큰이 확인되지 않음");
            }

            Boolean aBoolean = userService.UserPasswordChange(userId, Password, passwordEncoder);
            if (aBoolean)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "패스워드 변경 완료"));
            }
            else
            {
                throw new UpdateException();
            }

        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 에러" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @DeleteMapping
    public ResponseEntity<?> UserDelete(@AuthenticationPrincipal String userId, @RequestParam String Password)
    {
        try
        {
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("로그인 관련 에러 발생 : JWT 토큰이 확인되지 않음");
            }
            Boolean bool = userService.UserDelete(userId, Password, passwordEncoder );
            if (bool)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "패스워드 변경 완료"));
            }
            else
            {
                throw new UpdateException();
            }
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 에러" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> EmailCheck(@RequestParam String email)
    {
        try
        {
            boolean bool = userService.getUserID(email);
            if(bool)
            {
                return ResponseEntity.ok().body(responseDTO.Response("checknot", "이메일 중복!!"));
            }
            else
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "이메일 중복확인"));
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }

    @GetMapping("/nickcheck")
    public ResponseEntity<?> NicknameCheck(@RequestParam String nickname)
    {
        try
        {
            boolean bool = userService.getNicname(nickname);
            if(bool)
            {
                return ResponseEntity.ok().body(responseDTO.Response("checknot", "닉네임 중복!!"));
            }
            else
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "닉네임 중복확인됨, 사용 가능"));
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
        try{
            UserEntity user = userService.getByCredentials(userDTO.getUserId(), userDTO.getPassword(), passwordEncoder);
            if(user != null)
            {
                String token = tokenProvider.createToken(user);
                UserTokenDTO responseUserDTO = user.convertTo().convertTo(token);
                List<Object> list = new ArrayList<>();
                list.add(responseUserDTO);
                return ResponseEntity.ok().body(responseDTO.Response("success", "오늘도 저희 서비스에 방문해주셔서 감사드려요!!!", list));
            }
            else
            {
                throw new BadCredentialsException("회원정보가 존재하지 않습니다. 비밀번호나 아이디를 다시 입력해주세요.");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    //로그아웃
    @DeleteMapping("/signout")
    public ResponseEntity<?> signout(HttpServletResponse response)
    {
        // refreshToken 쿠키 삭제
        Cookie refreshTokenCookie = new Cookie("refreshToken", null); // 빈 쿠키를 새로 생성
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0); // 쿠키의 유효 시간을 0으로 설정, 브라우저가 즉시 이 쿠키를 삭제
        response.addCookie(refreshTokenCookie); // 수정된 쿠키(refreshTokenCookie)를 응답에 추가, 클라이언트에게 쿠키를 전송

        return ResponseEntity.ok().body(responseDTO.Response("success", "로그아웃 완료"));
    } // 프론트엔드 연결 후 기능 정상 동작 여부 확인해야 함.

}
