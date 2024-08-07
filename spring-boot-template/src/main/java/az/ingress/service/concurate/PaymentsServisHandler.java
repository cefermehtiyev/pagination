package az.ingress.service.concurate;

import az.ingress.dao.entity.PaymentEntity;
import az.ingress.dao.repository.PaymentRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.PaymentResponse;
import az.ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.ingress.model.enums.ExceptionConstants.PAYMENT_NOT_FOUND;
import static az.ingress.model.enums.PaymentStatus.DELETED;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentsServisHandler implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void cratePayment(PaymentRequest paymentRequest) {
        paymentRepository.save(PAYMENT_MAPPER.buildPaymentEntity(paymentRequest));
    }

    @Override
    public PaymentResponse getPayment(Long id) {
        var payment = fetchPaymentExist(id);
        return PAYMENT_MAPPER.buildPaymentResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll()
                .stream().map(PAYMENT_MAPPER::buildPaymentResponse).toList();
    }

    @Override
    public void updatePayment(Long id, PaymentRequest paymentRequest) {
        var payment = fetchPaymentExist(id);
        PAYMENT_MAPPER.updatePayment(payment,paymentRequest);
        paymentRepository.save(payment);

    }

    @Override
    public void deletePayment(Long id) {
        var payment = fetchPaymentExist(id);
        payment.setPaymentStatus(DELETED);
        paymentRepository.save(payment);

    }
    private PaymentEntity fetchPaymentExist(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(PAYMENT_NOT_FOUND.getCode(),PAYMENT_NOT_FOUND.getMessage() ));
    }
}
