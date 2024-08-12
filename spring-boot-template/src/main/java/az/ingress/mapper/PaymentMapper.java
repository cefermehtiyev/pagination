package az.ingress.mapper;

import az.ingress.dao.entity.PaymentEntity;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PaymentResponse;

import static az.ingress.model.enums.PaymentStatus.COMPLETED;

public enum PaymentMapper {
    PAYMENT_MAPPER;


    public PaymentEntity buildPaymentEntity(PaymentRequest paymentRequest) {
        return PaymentEntity.builder()
                .balance(paymentRequest.getBalance())
                .paymentStatus(COMPLETED)
                .build();
    }

    public PaymentResponse buildPaymentResponse(PaymentEntity paymentEntity) {
        return PaymentResponse.builder()
                .id(paymentEntity.getId())
                .balance(paymentEntity.getBalance())
                .paymentStatus(COMPLETED)
                .build();
    }

    public PaymentEntity updatePayment(PaymentEntity paymentEntity, PaymentRequest paymentRequest) {
        paymentEntity.setBalance(paymentRequest.getBalance());
        return paymentEntity;
    }

}
