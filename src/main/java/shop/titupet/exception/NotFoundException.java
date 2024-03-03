package shop.titupet.exception;

public class NotFoundException extends TitupetException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
