package moim.renew.backend.domain.User.UserMain.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service("google")
public class GoogleOAuth2Service implements OAuth2Service
{

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String requestAccessToken(String code) {
        try {
            URL url = new URL("https://oauth2.googleapis.com/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "code=" + URLEncoder.encode(code, StandardCharsets.UTF_8) +
                    "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                    "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8) +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8) +
                    "&grant_type=authorization_code";
            System.out.println("Request params: " + params);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(params.getBytes(StandardCharsets.UTF_8));
            }

            int status = conn.getResponseCode();
            InputStream inputStream = (status >= 200 && status < 300)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Google Token API Response: " + body);

            if (status >= 200 && status < 300) {
                Map<String, Object> response = objectMapper.readValue(body, Map.class);
                return (String) response.get("access_token");
            } else {
                throw new RuntimeException("Google token 요청 실패: " + body);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("Google access token 요청 실패", e);
        }
    }

    @Override
    public Map<String, Object> requestUserInfo(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v3/userinfo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            InputStream inputStream = conn.getInputStream();
            return objectMapper.readValue(inputStream, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Google 사용자 정보 요청 실패", e);
        }
    }
}
