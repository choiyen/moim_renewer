package moim.renew.backend.ConsultCategory.Controller;

import moim.renew.backend.ConsultCategory.DTO.ConsultCategoryDTO;
import moim.renew.backend.ConsultCategory.Service.ConsultCategoryService;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.User.Service.UserService;
import moim.renew.backend.config.DTO.ResponseDTO;
import moim.renew.backend.config.Enum.UserTypeEnum;
import moim.renew.backend.config.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/ConsultCategory")
@Validated
public class ConsultCategoryController
{
    private ResponseDTO responseDTO = new ResponseDTO();

    @Autowired
    private ConsultCategoryService consultCategoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> Insert(@AuthenticationPrincipal String userId, @RequestBody ConsultCategoryDTO consultCategoryDTO)
    {

            // 인증 사용자 확인
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                ConsultCategoryDTO consultCategoryDTO1 = consultCategoryService.Insert(consultCategoryDTO);
                return ResponseEntity.ok().body(responseDTO.Response("success", "게시판 카테고리 추가 완료", Collections.singletonList(consultCategoryDTO1)));
            }
            else
            {
                throw new BadCredentialsException("인증 권한을 보유하지 않은 사용자입니다 : 관리자만 확인 가능");
            }
    }

    @GetMapping
    public ResponseEntity<?> SelectAll()
    {
            List<ConsultCategoryDTO> consultCategoryDTOS = consultCategoryService.SelectAll();
            return ResponseEntity.ok().body(responseDTO.Response("success", "게시판 카테고리 조회 완료", consultCategoryDTOS));
    }

    @PutMapping
    public ResponseEntity<?> Update(@AuthenticationPrincipal String userId, @RequestBody ConsultCategoryDTO consultCategoryDTO)
    {

            // 인증 사용자 확인
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                ConsultCategoryDTO consultCategoryDTO1 = consultCategoryService.update(consultCategoryDTO);
                return ResponseEntity.ok().body(responseDTO.Response("success", "게시판 카테고리 업데이트 완료", Collections.singletonList(consultCategoryDTO1)));
            }
            else
            {
                throw new BadCredentialsException("인증 권한을 보유하지 않은 사용자입니다 : 관리자만 확인 가능");
            }
    }
    @DeleteMapping
    public ResponseEntity<?> Delete(@AuthenticationPrincipal String userId, @RequestParam Integer consultCategoryId)
    {

            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                Boolean bool = consultCategoryService.Delete(consultCategoryId);
                if(bool)
                {
                    return ResponseEntity.ok().body(responseDTO.Response("success", consultCategoryId + " 번호의 게시판 카테고리 삭제 완료"));
                }
                else
                {
                    throw new DeleteException("예기치 못한 오류로 삭제에 실패하였습니다.");
                }
            }
            else
            {
                throw new BadCredentialsException("인증 권한을 보유하지 않은 사용자입니다 : 관리자만 확인 가능");
            }
    }
}
