package shop.titupet.config.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TutipetException extends RuntimeException {
    public String code;
    public String message;
}
