package moim.renew.backend.User.Controller;

import moim.renew.backend.Security.TokenProvider;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.User.Service.OAuth2Service;
import moim.renew.backend.User.Service.OAuth2ServiceFactory;
import moim.renew.backend.User.Service.UserService;
import moim.renew.backend.config.DTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("api/Auth")
public class AuthController
{
    @Autowired
    private TokenProvider tokenProvider;

    private ResponseDTO responseDTO = new ResponseDTO<>();

    @Autowired
    private UserService userService;

    @Autowired
    private OAuth2ServiceFactory oAuth2ServiceFactory;

    @GetMapping("/sign/{oAuth2Type}")
    public ResponseEntity<?> oAuth2Sign(@RequestParam("code") String code, @PathVariable String oAuth2Type)
    {
        try
        {
            OAuth2Service service = oAuth2ServiceFactory.getService(oAuth2Type);
            if(service == null)
            {
                throw new BadCredentialsException("지원하지 않은 인증 방식입니다.");
            }

            String accessToken = service.requestAccessToken(code);
            Map<String, Object> userInfo = service.requestUserInfo(accessToken);

            String userId = extractUserIdFrom(userInfo, oAuth2Type);
            System.out.println("ssss "+ userId);
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO == null)
            {
                System.out.println("ooo" +userDTO);
                userDTO = userService.UserCreate(userInfo,oAuth2Type);
            }

            String jwt = tokenProvider.createToken(userDTO);
            //반환 받은 UserDTO를 토대로 jwt 토큰 생성

            return ResponseEntity.ok().body(responseDTO.Response("success", "OAuth2 인증 완료", Collections.singletonList(Map.of("token", "null", "user", userInfo))));
            //생성한 JWT 토큰을 프론트 엔드로 보냄
        }
        catch (Exception e)
        {
            System.out.println(e);
            return ResponseEntity.badRequest().body(responseDTO.Response("errorjjjjj", e.getMessage()));
        }
    }
    // OAuth2를 제공하는 서버에서 반환한 토큰에서 서버에 저장할 값을 반환 받는 코드
    public String extractUserIdFrom(Map<String, Object> userInfo, String provider) {
        switch (provider.toLowerCase()) {
            case "kakao":
                // kakao는 "id" 필드가 고유 ID
                Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
                return (String) kakaoAccount.get("email");
            case "naver":
                // naver는 "response" 내부에 있음
                Map<String, Object> naverResponse = (Map<String, Object>) userInfo.get("response");
                return (String) naverResponse.get("email");
            case "google":
                // google은 "sub" 필드가 고유 ID
                return (String) userInfo.get("email");
            default:
                throw new IllegalArgumentException("지원하지 않는 OAuth 타입입니다.");
        }
    }
}
