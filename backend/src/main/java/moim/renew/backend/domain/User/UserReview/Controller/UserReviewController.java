package moim.renew.backend.domain.User.UserReview.Controller;

import moim.renew.backend.domain.User.UserReview.DTO.UserReviewDTO;
import moim.renew.backend.domain.User.UserReview.Service.UserReviewService;
import moim.renew.backend.gobal.config.DTO.ResponseDTO;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/userReview")
public class UserReviewController
{
    @Autowired
    UserReviewService userReviewService;

    private final ResponseDTO responseDTO = new ResponseDTO<>();

    @PostMapping
    public ResponseEntity<?> Insert(@AuthenticationPrincipal String userId, @RequestBody UserReviewDTO userReviewDTO)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 로그인 정보 확인 불가");
            }

            UserReviewDTO userReviewDTO1 = userReviewService.InsertuserReview(userReviewDTO);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 저장 완료", Collections.singletonList(userReviewDTO1)));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@AuthenticationPrincipal String userId, @RequestBody UserReviewDTO userReviewDTO)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 로그인 정보 확인 불가");
            }

            UserReviewDTO userReviewDTO1 = userReviewService.UpdateuserReview(userReviewDTO);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트 완료", Collections.singletonList(userReviewDTO1)));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @DeleteMapping
    public ResponseEntity<?> Insert(@AuthenticationPrincipal String userId, @RequestParam String reviewee)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 로그인 정보 확인 불가");
            }

            boolean bool = userReviewService.DeleteuserReview(userId, reviewee);
            if(bool) {
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 삭제 완료"));
            }
            else
            {
                throw new DeleteException();
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> FindOfuserReview(@AuthenticationPrincipal String userId)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 로그인 정보 확인 불가");
            }
            List<UserReviewDTO> userReviewDTO = userReviewService.FindofuserReview(userId);
            if(userReviewDTO != null)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회 완료", userReviewDTO));
            }
            else
            {
                throw new SelectException();
            }
        }
        catch (Exception e) {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<?> FindOfuserReviewid(@AuthenticationPrincipal String userId, @RequestParam String id)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 로그인 정보 확인 불가");
            }
            UserReviewDTO userReviewDTO = userReviewService.FindofuserReviewId(id);
            if(userReviewDTO != null)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회 완료", Collections.singletonList(userReviewDTO)));
            }
            else
            {
                throw new SelectException();
            }
        }
        catch (Exception e) {
            return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
        }
    }
}
