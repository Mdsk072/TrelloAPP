package entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    private Set<String> bookmarkedBoards = new HashSet<>();
    private Set<String> bookmarkedLists = new HashSet<>();
    private Set<String> bookmarkedCards = new HashSet<>();

    public void bookmarkBoard(String boardId) {
        bookmarkedBoards.add(boardId);
    }

    public void bookmarkList(String listId) {
        bookmarkedLists.add(listId);
    }

    public void bookmarkCard(String cardId) {
        bookmarkedCards.add(cardId);
    }

    public Set<String> getBookmarkedBoards() {
        return bookmarkedBoards;
    }

    public Set<String> getBookmarkedLists() {
        return bookmarkedLists;
    }

    public Set<String> getBookmarkedCards() {
        return bookmarkedCards;
    }
}
