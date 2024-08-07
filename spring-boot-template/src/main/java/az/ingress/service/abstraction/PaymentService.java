package az.ingress.service.abstraction;

import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    void cratePayment(PaymentRequest paymentRequest);
    PaymentResponse getPayment(Long id);
    List<PaymentResponse> getAllPayments();
    void updatePayment(Long id,PaymentRequest paymentRequest);
    void deletePayment(Long id);

}
