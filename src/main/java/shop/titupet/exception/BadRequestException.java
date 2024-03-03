package shop.titupet.exception;

public class BadRequestException extends TitupetException {
    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
