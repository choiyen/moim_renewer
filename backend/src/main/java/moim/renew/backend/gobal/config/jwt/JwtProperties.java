package moim.renew.backend.gobal.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;

//    @PostConstruct
//    public void init() {
//        // HS512용 안전한 키를 서버 시작 시 무작위 생성
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        this.secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
//        System.out.println("Generated JWT Secret Key: " + this.secretKey); // 확인용
//    }
}

// application.properties 에 있는 설정 값을 가져오고자 하는 클래스