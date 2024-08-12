package az.ingress.controller;

import az.ingress.model.request.CardRequest;
import az.ingress.model.response.CardResponse;
import az.ingress.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/cards")
public class CardController {

    private final CardService cardService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCard(@RequestBody CardRequest cardRequest) {
        cardService.createCard(cardRequest);
    }

    @GetMapping("{id}")
    public CardResponse getCard(@PathVariable Long id){
        return cardService.getCard(id);
    }

    @GetMapping
    public List<CardResponse> getCards(){
        return cardService.getCards();
    }

    @PutMapping("{id}")
    public void updateCard(@PathVariable Long id,@RequestBody CardRequest cardRequest){
        cardService.updateCard(id,cardRequest);
    }

    @DeleteMapping("{id}")
    public void deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
    }



}
