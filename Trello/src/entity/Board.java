package entity;

import enums.Privacy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Board {
    private String boardId;
    private String name;
    private Privacy privacy;
    private List<User> members;
    private List<TrelloList> lists;

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public void addList(TrelloList list) {
        lists.add(list);
    }

    public void removeList(TrelloList list) {
        lists.remove(list);
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<TrelloList> getLists() {
        return lists;
    }

    public void setLists(List<TrelloList> lists) {
        this.lists = lists;
    }

    public Board(String boardId, String name) {
        this.boardId = boardId;
        this.name = name;
        this.lists = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId='" + boardId + '\'' +
                ", name='" + name + '\'' +
                ", privacy=" + privacy +
                ", members=" + members +
                ", lists=" + lists +
                '}';
    }
}
