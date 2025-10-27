package moim.renew.backend.gobal.Exception;

public class EmptyException extends RuntimeException
{
    public EmptyException(String message) {
        super(message);
    }
    public EmptyException() {
        super("관련 리스트나 베열이 비어있습니다.");
    }
}
