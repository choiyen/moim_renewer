package moim.renew.backend.Moim.Controller;

import moim.renew.backend.Moim.DTO.MoimDTO;
import moim.renew.backend.Moim.Service.MoimService;
import moim.renew.backend.config.DTO.ResponseDTO;
import moim.renew.backend.config.Exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/Moim")
public class MoimController
{
    @Autowired
    MoimService moimService;

    private final ResponseDTO responseDTO = new ResponseDTO();

    @PostMapping
    public ResponseEntity<?> MoimInsert(@AuthenticationPrincipal String userId, @RequestBody MoimDTO moimDTO)
    {
        try
        {
            // 인증 사용자 확인
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }

            MoimDTO moimDTO1 = moimService.Insert(moimDTO);
            return ResponseEntity.ok().body(responseDTO.Response("success", "모임 개설 완료", Collections.singletonList(moimDTO1)));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 인증 실패 : " + e.getMessage()));
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
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("현재 로그인이 된 사용자가 없습니다. 확인 후 다시 시도해주세요");
            }

            boolean bool = moimService.Delete(moimId, userId);

            if(bool)
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", moimId + "번호의 모임이 정상적으로 제거 되었습니다."));
            }
            else
            {
                throw new RuntimeException();
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> MoimGet(@AuthenticationPrincipal String userId, @RequestParam String moimId)
    {
        try
        {
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("현재 승인되어 있지 않은 JWT 패킷입니다.");
            }
            MoimDTO moimDTO = moimService.SelectById(moimId);
            return ResponseEntity.ok().body(responseDTO.Response("success", "모임 정보 조회 완료", Collections.singletonList(moimDTO)));

        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 인증 실패 : " + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> MoimALL()
    {
        try
        {
            List<MoimDTO> moimDTOS = moimService.SelectAll();
            if(moimDTOS.isEmpty())
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "정상적으로 모임 데이터를 찾았으나, 비어있습니다."));
            }
            else
            {
                return ResponseEntity.ok().body(responseDTO.Response("success", "모임 데이터 출력 완료", moimDTOS));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    public ResponseEntity<?> MoimUpdate(@AuthenticationPrincipal String userId, @RequestBody MoimDTO moimDTO)
    {
        try
        {
            if(userId == null || userId.isEmpty())
            {
                throw new BadCredentialsException("JWT 패킷에서 로그인 정보조회 실패");
            }
            MoimDTO moimDTO1 = moimService.Update(moimDTO);
            return ResponseEntity.ok().body(responseDTO.Response("success", "moim 업데이트 완료", Collections.singletonList(moimDTO1)));
        }
        catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 인증 실패 : " + e.getMessage()));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
        }
    }
}
