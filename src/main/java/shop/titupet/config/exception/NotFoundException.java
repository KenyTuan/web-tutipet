package shop.titupet.config.exception;

public class NotFoundException extends TutipetException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
