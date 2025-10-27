package moim.renew.backend.gobal.config.DTO;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDTO<T> {
    List<T> list;
    int PageCount;
    Long Total;
}
