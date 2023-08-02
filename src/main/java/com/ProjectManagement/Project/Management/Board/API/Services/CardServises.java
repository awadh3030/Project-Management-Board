package com.ProjectManagement.Project.Management.Board.API.Services;

import com.ProjectManagement.Project.Management.Board.API.Models.Card;
import com.ProjectManagement.Project.Management.Board.API.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CardServises {


    public CardRepository cardRepository;

    @Autowired
    public void CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getAllCards(Long boardId) {
        // Implement logic to retrieve all cards for a specific board
        return cardRepository.findByBoardId(boardId);
    }

    public Optional<Card> getCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }







}
