package service;

import entity.Board;
import entity.Card;
import entity.TrelloList;
import entity.User;
import repository.BoardRepository;
import repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

public class BookmarkService {
    private UserRepository userRepo;
    private BoardRepository boardRepo;

    public BookmarkService(UserRepository userRepo, BoardRepository boardRepo) {
        this.userRepo = userRepo;
        this.boardRepo = boardRepo;
    }

    public void bookmarkBoard(String userId, String boardId) {
        User user = userRepo.findById(userId);
        user.bookmarkBoard(boardId);
    }

    public void bookmarkList(String userId, String listId) {
        User user = userRepo.findById(userId);
        user.bookmarkList(listId);
    }

    public void bookmarkCard(String userId, String cardId) {
        User user = userRepo.findById(userId);
        user.bookmarkCard(cardId);
    }

    public Set<Board> getUserBookmarkedBoards(String userId) {
        User user = userRepo.findById(userId);
        Set<String> bookmarkedIds = user.getBookmarkedBoards();
        Set<Board> bookmarkedBoards = new HashSet<>();
        for (String id : bookmarkedIds) {
            bookmarkedBoards.add(boardRepo.findById(id));
        }
        return bookmarkedBoards;
    }

    public Set<TrelloList> getUserBookmarkedLists(String userId) {
        User user = userRepo.findById(userId);
        Set<String> bookmarkedIds = user.getBookmarkedLists();

        Set<TrelloList> bookmarkedLists = new HashSet<>();
        for (Board board : boardRepo.findAll()) {
            for (TrelloList list : board.getLists()) {
                if (bookmarkedIds.contains(list.getListId())) {
                    bookmarkedLists.add(list);
                }
            }
        }
        return bookmarkedLists;
    }

    public Set<Card> getUserBookmarkedCards(String userId) {
        User user = userRepo.findById(userId);
        Set<String> bookmarkedIds = user.getBookmarkedCards();

        Set<Card> bookmarkedCards = new HashSet<>();
        for (Board board : boardRepo.findAll()) {
            for (TrelloList list : board.getLists()) {
                for (Card card : list.getCards()) {
                    if (bookmarkedIds.contains(card.getCardId())) {
                        bookmarkedCards.add(card);
                    }
                }
            }
        }
        return bookmarkedCards;
    }
}
