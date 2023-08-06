package com.ProjectManagement.Project.Management.Board.API.Services;

import com.ProjectManagement.Project.Management.Board.API.Models.Board;
import com.ProjectManagement.Project.Management.Board.API.Models.Card;
import com.ProjectManagement.Project.Management.Board.API.Repositories.BoardRepository;
import com.ProjectManagement.Project.Management.Board.API.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CardServices {

    @Autowired
    public CardRepository cardRepository;
    @Autowired
    public BoardRepository boardRepository;


    public void CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getAllCards(Long boardId) {
        Board currBoard = boardRepository.findById(boardId).get();
        return currBoard.cards;
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
