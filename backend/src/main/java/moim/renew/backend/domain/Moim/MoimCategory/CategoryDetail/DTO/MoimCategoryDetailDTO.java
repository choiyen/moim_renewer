package moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import moim.renew.backend.domain.Moim.MoimCategory.CategoryDetail.Entity.MoimCategoryDetailEntity;

import java.security.SecureRandom;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MoimCategoryDetailDTO {

    @JsonProperty("MoimcategoryDetailId")
    private String categoryDetailId;
    @JsonProperty("MoimCategoryId")
    private Integer categoryId;
    @JsonProperty("MoimcategorisationDetail")
    private String categorisationDetail;

    public MoimCategoryDetailEntity ConvertTo()
    {
        // categoryDetailId가 null이면 랜덤 생성
        if (this.categoryDetailId == null || this.categoryDetailId.isEmpty()) {
            this.categoryDetailId = generateRandomId();
        }

        return MoimCategoryDetailEntity.builder()
                .categoryId(this.categoryId)
                .categoryDetailId(this.categoryDetailId)
                .categorisationDetail(this.categorisationDetail)
                .build();
    }
    public String generateRandomId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
    public static MoimCategoryDetailDTO of(String categoryDetailsId, Integer categorysId, String name) {
        return new MoimCategoryDetailDTO(categoryDetailsId,categorysId, name);
    }
}
