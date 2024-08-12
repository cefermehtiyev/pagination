package az.ingress.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionConstants {
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION","Unexpected exception"),
    ORDER_NOT_FOUND("ORDER_FOUND_EXCEPTION","Order Not Found "),
    CARD_NOT_FOUND("CARD_NOT_FOUND","Card Not Found "),
    PAYMENT_NOT_FOUND("PAYMENT_NOT_FOUND","Payment Not Found ");



    private final String code;
    private final String message;
}
