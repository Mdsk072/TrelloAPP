package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrelloList {
    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    private String listId;
    private String name;
    private List<Card> cards;

    public TrelloList(String listId, String name) {
        this.listId = listId;
        this.name = name;
        this.cards = new ArrayList<>();
    }
    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

}
