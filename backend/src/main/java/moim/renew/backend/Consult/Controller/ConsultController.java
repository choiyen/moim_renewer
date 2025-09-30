package moim.renew.backend.Consult.Controller;

import moim.renew.backend.Consult.DTO.ConsultDTO;
import moim.renew.backend.Consult.DTO.DeleteConsultDTO;
import moim.renew.backend.Consult.Service.ConsultService;
import moim.renew.backend.config.DTO.ResponseDTO;
import moim.renew.backend.config.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/consult")
public class ConsultController
{
    @Autowired
    private ConsultService consultService;

    private ResponseDTO responseDTO = new ResponseDTO();

    @PostMapping
    public ResponseEntity<ResponseDTO> Insert(@AuthenticationPrincipal String userId, @RequestBody ConsultDTO consultDTO)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        ConsultDTO consultDTO1 = consultService.Insert(consultDTO);
        return ResponseEntity.ok().body(responseDTO.Response("success", "모임 건의글 생성완료", Collections.singletonList(consultDTO1)));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> Delete(@AuthenticationPrincipal String userId, @RequestBody DeleteConsultDTO deleteConsultDTO)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        Boolean bool = consultService.Delete(deleteConsultDTO);
        if(bool)
        {
            return ResponseEntity.ok().body(responseDTO.Response("success", "정상적으로 데이터 삭제 완료"));
        }
        else
        {
            throw new DeleteException("데이터 삭제 실패");
        }
    }
    @PutMapping
    public ResponseEntity<ResponseDTO> Update(@AuthenticationPrincipal String userId, @RequestBody ConsultDTO consultDTO)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        ConsultDTO consultDTO1 = consultService.Update(consultDTO);
        return ResponseEntity.ok().body(responseDTO.Response("success", "모임 데이터 업데이트 완료", Collections.singletonList(consultDTO1)));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> Select(@AuthenticationPrincipal String userId, @RequestParam String ConsultId)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        ConsultDTO consultDTO = consultService.Select(ConsultId);
        return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회", Collections.singletonList(consultDTO)));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> SelectAll(@AuthenticationPrincipal String userId, @RequestParam Integer MoimCategoryId)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        List<ConsultDTO> consultDTOS = consultService.SelectAll(MoimCategoryId);
        return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회", consultDTOS));
    }

}
