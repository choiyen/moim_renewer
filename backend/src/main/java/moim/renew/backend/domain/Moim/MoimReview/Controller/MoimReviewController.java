package moim.renew.backend.domain.Moim.MoimReview.Controller;

import moim.renew.backend.domain.Moim.MoimReview.DTO.MoimReviewDTO;
import moim.renew.backend.domain.Moim.MoimReview.Service.MoimReviewService;
import moim.renew.backend.domain.User.UserMain.DTO.UserDTO;
import moim.renew.backend.domain.User.UserMain.Service.UserService;
import moim.renew.backend.gobal.config.DTO.ResponseDTO;
import moim.renew.backend.gobal.Exception.DeleteException;
import moim.renew.backend.gobal.Exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;


@RestController
@RequestMapping("/api/moimreview")
public class MoimReviewController
{
    private final ResponseDTO responseDTO = new ResponseDTO();

    @Autowired
    MoimReviewService moimReviewService;



    @GetMapping
    public ResponseEntity<?> MoimReviewSelect(@AuthenticationPrincipal String userId, @RequestParam String moimId)
    {
        try
        {
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("로그인을 위한 JWT 오류");
            }

            MoimReviewDTO moimReviewDTO = moimReviewService.getReviewByMoimId(moimId, userId);
            if (moimReviewDTO == null)
            {
                throw new SelectException("데이터를 찾을 수 없습니다.");
            }
            else
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회 완료", Collections.singletonList(moimReviewDTO)));
            }
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "모임 리뷰 가져오기 오류 : " + e.getMessage()));        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }

    @GetMapping("/avg")
    public ResponseEntity<?> MoimReviewAverAge(@RequestParam String moimId)
    {
        try
        {

            double AverAge = moimReviewService.getAverAgeScore(moimId);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회 완료", Collections.singletonList(AverAge)));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PutMapping("/Score")
    public ResponseEntity<?> MoimReviewScore(@AuthenticationPrincipal String userId, @RequestParam String moimId, @RequestParam Float score)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 인증 실패, 다시 로그인 시도해주세요");
            }
            MoimReviewDTO moimReviewDTO = moimReviewService.updateofScore(moimId, userId, score);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트 성공", Collections.singletonList(moimReviewDTO)));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 오류 :" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PutMapping("/score/and/comment")
    public ResponseEntity<?> MoimReviewAnd(@AuthenticationPrincipal String userId, @RequestParam String moimId, @RequestParam String comment, @RequestParam Float score)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 인증 실패, 다시 로그인 시도해주세요");
            }
            MoimReviewDTO moimReviewDTO = moimReviewService.updateofScoreAndComment(moimId, userId, score, comment);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트 성공", Collections.singletonList(moimReviewDTO)));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 오류 :" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PutMapping("/comment")
    public ResponseEntity<?> MoimReviewcomment(@AuthenticationPrincipal String userId, @RequestParam String moimId, @RequestParam String comment)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 인증 실패, 다시 로그인 시도해주세요");
            }
            MoimReviewDTO moimReviewDTO = moimReviewService.updateofcomment(moimId, userId, comment);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트 성공", Collections.singletonList(moimReviewDTO)));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 오류 :" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @PostMapping
    public ResponseEntity<?> MoimInsert(@AuthenticationPrincipal String userId, @RequestBody MoimReviewDTO moimReviewDTO)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 관련 오류");
            }
            MoimReviewDTO moimReviewDTO1 = moimReviewService.Insert(moimReviewDTO);
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 저장 완료", Collections.singletonList(moimReviewDTO1)));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 오류 :" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @DeleteMapping
    public ResponseEntity<?> MoimDelete(@AuthenticationPrincipal String userId, @RequestParam String moimId)
    {
        try
        {
            if(userId != null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 관련 오류");
            }
            boolean bool = moimReviewService.Delete(moimId, userId);
            if(bool)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 삭제 완료"));
            }
            else
            {
                throw new DeleteException();
            }
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 관련 오류 :" + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }

}
