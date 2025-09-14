package moim.renew.backend.MoimCategory.Category.Controller;

import moim.renew.backend.MoimCategory.Category.DTO.MoimCateGoryDTO;
import moim.renew.backend.MoimCategory.Category.Service.MoimCateGoryService;
import moim.renew.backend.User.DTO.UserDTO;
import moim.renew.backend.User.Entity.UserEntity;
import moim.renew.backend.User.Service.UserService;
import moim.renew.backend.config.DTO.ResponseDTO;
import moim.renew.backend.config.Enum.UserTypeEnum;
import moim.renew.backend.config.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class MoimCategoryController
{
    @Autowired
    private MoimCateGoryService moimCateGoryService;

    @Autowired
    private UserService userService;

    private ResponseDTO responseDTO = new ResponseDTO();


    @PostMapping
    public ResponseEntity<?> Insert(@AuthenticationPrincipal String userId, @RequestBody MoimCateGoryDTO moimCateGoryDTO)
    {
        try
        {
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                MoimCateGoryDTO moimCateGoryDTO1 = moimCateGoryService.Insert(moimCateGoryDTO);
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 추가", Collections.singletonList(moimCateGoryDTO1)));
            }
            else
            {
                throw new BadCredentialsException("카테고리를 추가할 권한이 없습니다.");

            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", responseDTO.getMessage()));
        }
    }
    @PutMapping
    public ResponseEntity<?> Update(@AuthenticationPrincipal String userId, @RequestBody MoimCateGoryDTO moimCateGoryDTO)
    {
        try
        {
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                MoimCateGoryDTO moimCateGoryDTO1 = moimCateGoryService.Update(moimCateGoryDTO);
                return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트", Collections.singletonList(moimCateGoryDTO1)));
            }
            else
            {
                throw new BadCredentialsException("카테고리를 추가할 권한이 없습니다.");

            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", responseDTO.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> Delete(@AuthenticationPrincipal String userId, @RequestBody Integer MoimCategoryId)
    {
        try
        {
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                boolean bool = moimCateGoryService.Delete(MoimCategoryId);
                if(bool)
                {
                    return ResponseEntity.ok().body(responseDTO.Response("success", "카테고리 주 데이터 삭제 완료"));
                }
                else
                {
                    throw new DeleteException("데이터 삭제 실패");
                }
            }
            else
            {
                throw new BadCredentialsException("카테고리를 추가할 권한이 없습니다.");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", responseDTO.getMessage()));
        }
    }
    @GetMapping
    public ResponseEntity<?> Getting()
    {
        try
        {
            List<MoimCateGoryDTO> moimCateGoryDTOS = moimCateGoryService.SelectAll();
            return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 조회", moimCateGoryDTOS));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(responseDTO.Response("error", responseDTO.getMessage()));
        }
    }
}
