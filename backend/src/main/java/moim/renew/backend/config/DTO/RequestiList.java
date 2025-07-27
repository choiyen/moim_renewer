package moim.renew.backend.config.DTO;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Builder
@Data
public class RequestiList<T> {
    private T data;
    private Pageable pageable;
}
