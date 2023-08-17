package service;

import entity.Board;
import entity.Card;
import entity.TrelloList;
import entity.User;
import repository.BoardRepository;

public class CardService {
    private BoardRepository boardRepository;

    public CardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Card createCard(String boardId, String listId, String cardId, String name, String description, int priority) {
        Board board = boardRepository.findById(boardId);
        TrelloList list = board.getLists().stream().filter(l -> l.getListId().equals(listId)).findFirst().orElse(null);
        if (list != null) {
            Card card = new Card(cardId, name, description);
            card.setPriority(priority);
            list.getCards().add(card);
            return card;
        }
        return null;
    }
    public void assignUserToCard(String boardId, String listId, String cardId, User user) {
        Card card = findCard(boardId, listId, cardId);
        if (card != null) {
            card.assignUser(user);
        }
    }

    private Card findCard(String boardId, String listId, String cardId) {
        Board board = boardRepository.findById(boardId);
        for (TrelloList list : board.getLists()) {
            if (list.getListId().equals(listId)) {
                for (Card card : list.getCards()) {
                    if (card.getCardId().equals(cardId)) {
                        return card;
                    }
                }
            }
        }
        return null;
    }

    public void deleteCard(String boardId, String listId, String cardId) {
        TrelloList list = new ListService(boardRepository).getList(boardId, listId);
        Card targetCard = null;
        for (Card card : list.getCards()) {
            if (card.getCardId().equals(cardId)) {
                targetCard = card;
                break;
            }
        }
        if (targetCard != null) {
            list.removeCard(targetCard);
        }
    }
    public void moveCard(String boardId, String fromListId, String toListId, String cardId) {
        TrelloList fromList = new ListService(boardRepository).getList(boardId, fromListId);
        TrelloList toList = new ListService(boardRepository).getList(boardId, toListId);
        Card targetCard = null;
        for (Card card : fromList.getCards()) {
            if (card.getCardId().equals(cardId)) {
                targetCard = card;
                break;
            }
        }
        if (targetCard != null) {
            fromList.removeCard(targetCard);
            toList.addCard(targetCard);
        }
    }

    public Card getCard(String boardId, String listId, String cardId) {
        TrelloList list = new ListService(boardRepository).getList(boardId, listId);
        for (Card card : list.getCards()) {
            if (card.getCardId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }

}
