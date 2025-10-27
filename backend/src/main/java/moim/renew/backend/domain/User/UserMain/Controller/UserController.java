package moim.renew.backend.domain.User.UserMain.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moim.renew.backend.gobal.S3Service.S3ImageService;
import moim.renew.backend.gobal.Security.TokenProvider;
import moim.renew.backend.domain.User.UserMain.DTO.LoginDTO;
import moim.renew.backend.domain.User.UserMain.DTO.UserDTO;
import moim.renew.backend.domain.User.UserMain.DTO.UserTokenDTO;
import moim.renew.backend.domain.User.UserMain.Service.UserService;
import moim.renew.backend.gobal.config.DTO.ResponseDTO;
import moim.renew.backend.gobal.Exception.InsertException;
import moim.renew.backend.gobal.Exception.SelectException;
import moim.renew.backend.gobal.Exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Validated
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
                return ResponseEntity.ok().body(responseDTO.Response("success", "회원정보를 반환합니다.", Collections.singletonList(userDTO)));
            }
            else
            {
                throw new SelectException("유저 정보를 찾는데 실패하였습니다. 아무래도 JWT 토큰이 만료된 것 같아요");
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

    @GetMapping("/mailcheck")
    public ResponseEntity<?> EmailCheck(@RequestParam String email) {
        try {
            // 1. 빈 값 체크
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        responseDTO.Response("error", "이메일은 비어있을 수 없습니다.")
                );
            }

            // 2. 길이 체크
            if (email.length() > 50) {
                return ResponseEntity.badRequest().body(
                        responseDTO.Response("error", "이메일은 50자 이하로 입력해야 합니다.")
                );
            }

            // 3. 이메일 형식 체크 (간단한 정규식)
            if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return ResponseEntity.badRequest().body(
                        responseDTO.Response("error", "이메일 형식이 올바르지 않습니다.")
                );
            }

            // 4. 중복 체크
            boolean exists = userService.getUserID(email);
            if (exists) {
                return ResponseEntity.ok().body(
                        responseDTO.Response("checknot", "이메일이 이미 사용 중입니다.")
                );
            } else {
                return ResponseEntity.ok().body(
                        responseDTO.Response("success", "사용 가능한 이메일입니다.")
                );
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    responseDTO.Response("error", e.getMessage())
            );
        }
    }


    @GetMapping("/nickcheck")
    public ResponseEntity<?> NicknameCheck(@RequestParam String nickname)
    {
        try
        {


            // 1. 빈 값 체크
            if (nickname == null || nickname.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(
                        responseDTO.Response("error", "닉네임은 비어있을 수 없습니다.")
                );
            }

            // 2. 길이 체크
            if (nickname.length() < 5 || nickname.length() > 20) {
                return ResponseEntity.badRequest().body(
                        responseDTO.Response("error", "닉네임은 5자 이상 20자 이하로 입력해야 합니다.")
                );
            }

            // 3. 정규식 체크 (한글, 영어, 숫자만 허용)
            if (!nickname.matches("^[a-zA-Z0-9가-힣]+$")) {
                return ResponseEntity.badRequest().body(
                        responseDTO.Response("error", "닉네임은 한글, 영어, 숫자만 입력할 수 있습니다.")
                );
            }


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
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO){
        try{
            UserDTO user = userService.getByCredentials(loginDTO.getUserId(), loginDTO.getPassword(), passwordEncoder);
            if(user != null)
            {
                String token = tokenProvider.createToken(user);
                UserTokenDTO responseUserDTO = user.convertTo(token);
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

    @GetMapping("/check")
    public ResponseEntity<ResponseDTO> checkLogin() {
        boolean loggedIn = userService.isLoggedIn();
        if (loggedIn) {
            return ResponseEntity.ok().body(responseDTO.Response("success", "로그인 상태입니다 ✅"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(responseDTO.Response("error", "로그인이 필요합니다 ❌"));
        }
    }



}
