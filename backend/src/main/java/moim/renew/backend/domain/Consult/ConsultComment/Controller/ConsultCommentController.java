package moim.renew.backend.domain.Consult.ConsultComment.Controller;

import moim.renew.backend.domain.Consult.ConsultComment.DTO.ConsultCommentDTO;
import moim.renew.backend.domain.Consult.ConsultComment.DTO.DeletedCommentDTO;
import moim.renew.backend.domain.Consult.ConsultComment.Service.ConsultCommentService;
import moim.renew.backend.gobal.config.DTO.ResponseDTO;
import moim.renew.backend.gobal.Exception.SelectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/consultComment")
@Validated
public class ConsultCommentController
{
    @Autowired
    private ConsultCommentService consultCommentService;

    private ResponseDTO responseDTO = new ResponseDTO();

    @GetMapping
    public ResponseEntity<ResponseDTO> Select(@AuthenticationPrincipal String userId, @RequestParam String MoimConsultCommentId)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        ConsultCommentDTO consultCommentDTO = consultCommentService.Select(MoimConsultCommentId);
        return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회", Collections.singletonList(consultCommentDTO)));
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> SelectAll(@AuthenticationPrincipal String userId, @RequestParam String MoimConsultId)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        List<ConsultCommentDTO> consultCommentDTOS = consultCommentService.SelectAll(MoimConsultId);
        return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회", consultCommentDTOS));
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> Insert(@AuthenticationPrincipal String userId, @RequestBody ConsultCommentDTO consultCommentDTO)
    {
        System.out.println(consultCommentDTO);
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        ConsultCommentDTO consultCommentDTO1 = consultCommentService.Insert(consultCommentDTO);
        return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 삽입", Collections.singletonList(consultCommentDTO1)));
    }
    @PutMapping
    public ResponseEntity<ResponseDTO> Update(@AuthenticationPrincipal String userId, @RequestBody ConsultCommentDTO consultCommentDTO)
    {
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        ConsultCommentDTO consultCommentDTO1 = consultCommentService.Update(consultCommentDTO);
        return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트", Collections.singletonList(consultCommentDTO1)));
    }
    @DeleteMapping
    public ResponseEntity<ResponseDTO> Delete(@AuthenticationPrincipal String userId,  @RequestBody DeletedCommentDTO deletedCommentDTO)
    {
        System.out.println(deletedCommentDTO);
        if(userId == null || userId.isEmpty())
        {
            throw new BadCredentialsException("로그인이 되어 있지 않은 회원은 문의글을 남길 수 없습니다.");
        }
        Boolean bool = consultCommentService.Delete(deletedCommentDTO);
        if(bool)
        {
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트"));
        }
        else
        {
            throw new SelectException();
        }
    }
}
