package az.ingress.model.response;

import az.ingress.model.enums.CardStatus;
import az.ingress.model.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardResponse {
    private Long id;
    private String pan;
    private LocalDate expiryDate;
    private String cvv;
    private String cardHolder;
    private CardStatus cardStatus;


}
