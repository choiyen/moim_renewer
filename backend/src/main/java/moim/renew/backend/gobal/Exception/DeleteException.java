package moim.renew.backend.gobal.Exception;

public class DeleteException extends RuntimeException {
    public DeleteException(String message) {
        super(message);
    }
    public DeleteException() {super("데이터를 삭제하던 도중 예기치 못한 오류가 발생하였습니다.");}
}
