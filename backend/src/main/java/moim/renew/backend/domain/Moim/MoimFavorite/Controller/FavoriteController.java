package moim.renew.backend.domain.Moim.MoimFavorite.Controller;

import moim.renew.backend.domain.Moim.MoimFavorite.DTO.FavoriteDTO;
import moim.renew.backend.domain.Moim.MoimFavorite.Service.FavoriteService;
import moim.renew.backend.gobal.config.DTO.ResponseDTO;
import moim.renew.backend.gobal.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/favorite")
public class FavoriteController
{
    @Autowired
    FavoriteService favoriteService;

    private final ResponseDTO responseDTO = new ResponseDTO();


   @GetMapping
   public ResponseEntity<?> FavoriteGet(@AuthenticationPrincipal String userId)
   {
       try
       {
           if(userId == null || userId.isEmpty())
           {
               throw new BadCredentialsException("JWT 토큰이 인증되지 않음");
           }

           List<FavoriteDTO> favoriteDTO = favoriteService.selectoffavorite(userId);

           return ResponseEntity.ok().body(responseDTO.Response("success", "찜 목록 불러오기 완료", favoriteDTO));

       }
       catch (AuthenticationException e)
       {
           return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 X 에러 유형 :" + e.getMessage()));
       }
       catch (Exception e)
       {
           return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
       }
   }
   @PostMapping
   public ResponseEntity<?> FavoriteInsert(@AuthenticationPrincipal String userId, @RequestParam String moimId)
   {
       try
       {
           if(userId == null || userId.isEmpty())
           {
               throw new BadCredentialsException("JWT 토큰이 인증되지 않음");
           }
           FavoriteDTO favoriteDTO = FavoriteDTO.builder()
                   .userId(userId)
                   .moimId(moimId)
                   .build();
           FavoriteDTO favoriteDTO2 = favoriteService.Insertfavoriteofmoim(favoriteDTO);
           return ResponseEntity.ok().body(responseDTO.Response("success", "찜 완료", Collections.singletonList(favoriteDTO2)));
       }
       catch (AuthenticationException e)
       {
           return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 X 에러 유형 :" + e.getMessage()));
       }
       catch (Exception e)
       {
           return ResponseEntity.badRequest().body(responseDTO.Response("error", e.getMessage()));
       }
   }

   @DeleteMapping
   public ResponseEntity<?> FavoriteDelete(@AuthenticationPrincipal String userId, @RequestParam String moimId)
   {
       try
       {
           if(userId == null || userId.isEmpty())
           {
               throw new BadCredentialsException("JWT 토큰이 인증되지 않음");
           }
           boolean bool = favoriteService.Deleteoffavorite(moimId, userId);
           if(bool)
           {
               return ResponseEntity.ok().body(responseDTO.Response("success", "찜 목록 제거 완료"));
           }
           else
           {
               throw new DeleteException();
           }
       }
       catch (AuthenticationException e)
       {
           return ResponseEntity.badRequest().body(responseDTO.Response("error", "로그인 X 에러 유형 :" + e.getMessage()));
       }
       catch (Exception e)
       {
           return ResponseEntity.ok().body(responseDTO.Response("error", e.getMessage()));
       }
   }
}
