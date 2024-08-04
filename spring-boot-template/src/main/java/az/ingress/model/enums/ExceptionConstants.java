package az.ingress.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionConstants {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION","Unexpected exception"),
    NOT_FOUND_EXCEPTION("NOT_FOUND_EXCEPTION","Not Found Exception");

    private final String code;
    private final String message;
}
