package shop.titupet.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TitupetException extends RuntimeException {
    public String code;
    public String message;
}
