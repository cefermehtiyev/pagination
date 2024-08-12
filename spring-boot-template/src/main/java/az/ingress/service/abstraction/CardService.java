package az.ingress.service.abstraction;

import az.ingress.model.request.CardRequest;
import az.ingress.model.response.CardResponse;

import java.util.List;

public interface CardService {
    void createCard(CardRequest cardRequest);
    CardResponse getCard(Long id);
    List<CardResponse> getCards();
    void updateCard(Long id, CardRequest cardRequest);
    void deleteCard(Long id);



}
