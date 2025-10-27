package moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Controller;


import moim.renew.backend.domain.Moim.MoimCategory.Category.Service.MoimCateGoryService;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.DTO.DeleteCategoryDetailRequest;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.DTO.GettingCategoryRequest;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.DTO.MoimCategoryDetailDTO;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Service.MoimCategoryDetaillService;
import moim.renew.backend.domain.User.UserMain.DTO.UserDTO;
import moim.renew.backend.domain.User.UserMain.Service.UserService;
import moim.renew.backend.gobal.config.DTO.ResponseDTO;
import moim.renew.backend.gobal.config.Enum.UserTypeEnum;
import moim.renew.backend.gobal.Exception.DeleteException;
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
@RequestMapping("/api/CategoryDetail")
@Validated
public class CategoryDetailController
{
    @Autowired
    private MoimCategoryDetaillService moimCategoryDetaillService;

    @Autowired
    private UserService userService;

    @Autowired
    private MoimCateGoryService moimCateGoryService;

    private ResponseDTO responseDTO = new ResponseDTO();


    @PostMapping
    public ResponseEntity<?> Insert( @RequestBody MoimCategoryDetailDTO moimCategoryDetailDTO, @AuthenticationPrincipal String userId)
    {

            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                if(moimCateGoryService.SelectCounting(moimCategoryDetailDTO.getCategoryId()))
                {
                    MoimCategoryDetailDTO moimCategoryDetailDTO1 = moimCategoryDetaillService.Insert(moimCategoryDetailDTO);
                    return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 추가", Collections.singletonList(moimCategoryDetailDTO1)));
                }
                else
                {
                    throw new SelectException("해당 넘버를 가진 모임 카테고리를 찾을 수 없습니다.");
                }
            }
            else
            {
                throw new BadCredentialsException("메니저 권한을 가진 아이디가 아닙니다.");
            }
    }
    @PutMapping
    public ResponseEntity<?> Update(@AuthenticationPrincipal String userId, @RequestBody MoimCategoryDetailDTO moimCategoryDetailDTO)
    {

            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                if(moimCateGoryService.SelectCounting(moimCategoryDetailDTO.getCategoryId()))
                {
                    MoimCategoryDetailDTO moimCategoryDetailDTO1 = moimCategoryDetaillService.Update(moimCategoryDetailDTO);
                    return ResponseEntity.ok().body(responseDTO.Response("success", "데이터 업데이트", Collections.singletonList(moimCategoryDetailDTO1)));
                }
                else
                {
                    throw new SelectException("해당 넘버를 가진 모임 카테고리를 찾을 수 없습니다.");
                }
            }
            else
            {
                throw new BadCredentialsException("메니저 권한을 가진 아이디가 아닙니다.");
            }
    }

    @PostMapping("/get")
    public ResponseEntity<?> Getting(@AuthenticationPrincipal String userId, @RequestBody GettingCategoryRequest gettingCategoryRequest)
    {
        System.out.println(gettingCategoryRequest);
            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            List<MoimCategoryDetailDTO> moimCategoryDetailDTOS = moimCategoryDetaillService.SelectAll(gettingCategoryRequest.getMoimCategoryId());
            return ResponseEntity.ok().body(responseDTO.Response("success", "모임 세부 카테고리 조회", moimCategoryDetailDTOS));
    }

    @DeleteMapping
    public ResponseEntity<?> Delete(@AuthenticationPrincipal String userId, @RequestBody DeleteCategoryDetailRequest detailRequest)
    {

            if (userId == null || userId.isEmpty()) {
                throw new BadCredentialsException("로그인된 사용자 정보가 없습니다.");
            }
            UserDTO userDTO = userService.FindUserID(userId);
            if(userDTO.getType() == UserTypeEnum.MANAGER)
            {
                Boolean bool = moimCategoryDetaillService.Delete(detailRequest.getCategoryDetailId());
                if(bool)
                {
                    return ResponseEntity.ok().body(responseDTO.Response("success", "카테고리 데이터 삭제 완료"));
                }
                else
                {
                    throw new DeleteException("데이터 삭제 실패");
                }
            }
            else
            {
                throw new BadCredentialsException("메니저 권한을 가진 아이디가 아닙니다.");
            }
    }

}
