package az.ingress.controller;

import az.ingress.model.request.CardRequest;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.CardResponse;
import az.ingress.model.response.PaymentResponse;
import az.ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/payments")
public class PaymentController {
    private final PaymentService  paymentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.cratePayment(paymentRequest);
    }

    @GetMapping("{id}")
    public PaymentResponse getPayment(@PathVariable Long id){
        return paymentService.getPayment(id);
    }

    @GetMapping
    public List<PaymentResponse> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @PutMapping("{id}")
    public void updatePayment(@PathVariable Long id,@RequestBody PaymentRequest paymentRequest){
        paymentService.updatePayment(id,paymentRequest);
    }

    @DeleteMapping("{id}")
    public void deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
    }
}
