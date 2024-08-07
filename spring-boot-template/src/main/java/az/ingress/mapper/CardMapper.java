package az.ingress.mapper;

import az.ingress.dao.entity.CardEntity;
import az.ingress.dao.entity.PaymentEntity;
import az.ingress.dao.entity.UserEntity;
import az.ingress.model.enums.CardStatus;
import az.ingress.model.request.CardRequest;
import az.ingress.model.request.PaymentRequest;
import az.ingress.model.response.CardResponse;

import java.math.BigDecimal;

import static az.ingress.model.enums.CardStatus.ACTIVE;


public enum CardMapper {

    CARD_MAPPER;


    public CardEntity buildCardEntity(CardRequest cardRequest) {
        return CardEntity.builder()
                .pan(cardRequest.getPan())
                .expiryDate(cardRequest.getExpiryDate())
                .cvv(cardRequest.getCvv())
                .cardHolder(cardRequest.getCardHolder())
                .cardStatus(ACTIVE)
                .build();
    }

    public CardResponse buildCardResponse(CardEntity cardEntity) {
        return CardResponse.builder()
                .id(cardEntity.getId())
                .pan(cardEntity.getPan())
                .expiryDate(cardEntity.getExpiryDate())
                .cvv(cardEntity.getCvv())
                .cardHolder(cardEntity.getCardHolder())
                .cardStatus(ACTIVE)
                .build();
    }
    public CardEntity updateCard(CardEntity cardEntity, CardRequest cardRequest) {
        cardEntity.setPan(cardRequest.getPan());
        cardEntity.setExpiryDate(cardRequest.getExpiryDate());
        cardEntity.setCvv(cardRequest.getCvv());
        cardEntity.setCardHolder(cardRequest.getCardHolder());
        return cardEntity;
    }


}
