package az.ingress.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum ExceptionConstants {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION","Unexpected exception"),
    NOT_FOUND_EXCEPTION("NOT_FOUND_EXCEPTION","Not Found Exception");

    String code;
    String message;
}
