package shop.titupet.config.exception;

public class BadRequestException extends TutipetException {
    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
