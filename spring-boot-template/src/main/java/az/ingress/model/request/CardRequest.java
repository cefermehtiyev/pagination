package az.ingress.model.request;

import az.ingress.model.enums.CardStatus;
import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private String pan;
    private LocalDate expiryDate;
    private String cvv;
    private String cardHolder;
    PaymentRequest paymentRequest;

}
