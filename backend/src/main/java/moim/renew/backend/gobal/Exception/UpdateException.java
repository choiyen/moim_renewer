package moim.renew.backend.gobal.Exception;

public class UpdateException extends RuntimeException {
    public UpdateException(String message) {
        super(message);
    }
    public UpdateException() { super("데이터 정보 변경 도중에 예기치 못한 오류가 발생하였습니다.");}
}
