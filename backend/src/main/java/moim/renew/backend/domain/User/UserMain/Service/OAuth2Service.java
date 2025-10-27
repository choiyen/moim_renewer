package moim.renew.backend.domain.User.UserMain.Service;

import java.util.Map;

public interface OAuth2Service
{
    String requestAccessToken(String code);
    Map<String, Object> requestUserInfo(String accessToken);
}
