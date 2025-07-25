package moim.renew.backend.config.Exception;

public class InsertException extends RuntimeException {
    public InsertException(String message) {
      super(message);
    }
    public InsertException() {
      super("데이터 추가 도중에 오류가 발생하였습니다. 관리자에게 문의하세요.");
    }
}
