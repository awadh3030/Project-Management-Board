package com.ProjectManagement.Project.Management.Board.API.Controllers;

import com.ProjectManagement.Project.Management.Board.API.Models.Card;
import com.ProjectManagement.Project.Management.Board.API.RequestObjects.GetCardRequestObjects;
import com.ProjectManagement.Project.Management.Board.API.ResponseObjects.MessageResponse;
import com.ProjectManagement.Project.Management.Board.API.Services.CardServises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards/{id}/cards")
@CrossOrigin("*")
public class CardControllers {

    @Autowired
    public CardServises cardServices;



    @PostMapping
    public ResponseEntity<Card> createCard(
            @PathVariable("board_id") Long boardId,
            @RequestBody GetCardRequestObjects.CardRequest cardRequest) {

        Card newCard = new Card();
        newCard.setTitle(cardRequest.getTitle());
        newCard.setDescription(cardRequest.getDescription());
        newCard.setSection(cardRequest.getSection());

        Card savedCard = cardServices.saveCard(newCard);

        return ResponseEntity.ok(savedCard);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCards(@PathVariable("board_id") Long boardId) {
        List<Card> cards = cardServices.getAllCards(boardId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{card_id}")
    public ResponseEntity<Card> getCardById(
            @PathVariable("board_id") Long boardId,
            @PathVariable("card_id") Long cardId) {

        Optional<Card> optionalCard = cardServices.getCardById(cardId);
        return optionalCard.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{card_id}")
    public ResponseEntity<Card> updateCard(
            @PathVariable("board_id") Long boardId,
            @PathVariable("card_id") Long cardId,
            @RequestBody GetCardRequestObjects.CardRequest cardRequest) {

        Optional<Card> optionalCard = cardServices.getCardById(cardId);
        if (optionalCard.isPresent()) {
            Card existingCard = optionalCard.get();
            existingCard.setTitle(cardRequest.getTitle());
            existingCard.setDescription(cardRequest.getDescription());
            existingCard.setSection(cardRequest.getSection());

            Card updatedCard = cardServices.saveCard(existingCard);
            return ResponseEntity.ok(updatedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{card_id}")
    public ResponseEntity<MessageResponse> deleteCard(
            @PathVariable("board_id") Long boardId,
            @PathVariable("card_id") Long cardId) {

        Optional<Card> optionalCard = cardServices.getCardById(cardId);
        if (optionalCard.isPresent()) {
            Card cardToDelete = optionalCard.get();
            cardServices.deleteCard(cardToDelete);
            return ResponseEntity.ok(new MessageResponse("Card with ID " + cardId + " has been deleted successfully from board " + boardId + "."));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
