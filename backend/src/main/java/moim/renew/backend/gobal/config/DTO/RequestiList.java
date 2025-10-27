package moim.renew.backend.gobal.config.DTO;


import lombok.Builder;
import lombok.Data;

import java.awt.print.Pageable;

@Builder
@Data
public class RequestiList<T> {
    private T data;
    private Pageable pageable;
}
