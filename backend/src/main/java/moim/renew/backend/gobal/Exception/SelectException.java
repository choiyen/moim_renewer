package moim.renew.backend.gobal.Exception;

public class SelectException extends RuntimeException
{
    // 기본 생성자
    public SelectException() {
        super("MoimId에 맞는 모임 정보를 찾을 수 없습니다. 죄송합니다.");
    }

    // 메시지를 받는 생성자
    public SelectException(String message) {
        super(message);
    }

    // 원인(Throwable)을 받는 생성자
    public SelectException(String message, Throwable cause) {
        super(message, cause);
    }

    // 원인(Throwable)만 받는 생성자
    public SelectException(Throwable cause) {
        super(cause);
    }
}
