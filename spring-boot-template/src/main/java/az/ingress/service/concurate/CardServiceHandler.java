package az.ingress.service.concurate;

import az.ingress.dao.entity.CardEntity;
import az.ingress.dao.repository.CardRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.mapper.CardMapper;
import az.ingress.model.enums.CardStatus;
import az.ingress.model.enums.ExceptionConstants;
import az.ingress.model.request.CardRequest;
import az.ingress.model.response.CardResponse;
import az.ingress.service.abstraction.CardService;
import az.ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.mapper.CardMapper.CARD_MAPPER;
import static az.ingress.model.enums.CardStatus.INACTIVE;
import static az.ingress.model.enums.ExceptionConstants.CARD_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardServiceHandler implements CardService {
    private final CardRepository cardRepository;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public void createCard(CardRequest cardRequest) {
        log.info("ActionLog.createCard.start :{}", cardRequest);
        cardRepository.save(CARD_MAPPER.buildCardEntity(cardRequest));
        paymentService.cratePayment(cardRequest.getPaymentRequest());
        log.info("ActionLog.createCard.success :{}", cardRequest);


    }

    @Override
    public CardResponse getCard(Long id) {
        var card = fetchCardExist(id);
        return CARD_MAPPER.buildCardResponse(card);
    }

    @Override
    public List<CardResponse> getCards() {
        return cardRepository.findAll().stream().map(CARD_MAPPER::buildCardResponse).toList();
    }

    @Override
    @Transactional
    public void updateCard(Long id, CardRequest cardRequest) {
        var card = fetchCardExist(id);
        CARD_MAPPER.updateCard(card, cardRequest);
        cardRepository.save(card);
        paymentService.updatePayment(id, cardRequest.getPaymentRequest());



    }

    @Override
    @Transactional
    public void deleteCard(Long id) {
        var card = fetchCardExist(id);
        card.setCardStatus(INACTIVE);
        cardRepository.save(card);
        paymentService.deletePayment(id);
    }

    private CardEntity fetchCardExist(Long id) {
       return cardRepository.findById(id).
               orElseThrow(()->new NotFoundException(CARD_NOT_FOUND.getCode(),CARD_NOT_FOUND.getMessage() )
       );
    }


}
