package service;

import entity.Board;
import entity.TrelloList;
import repository.BoardRepository;

public class ListService {
    private BoardRepository boardRepository;

    public ListService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public TrelloList createList(String boardId, String listId, String listName) {
        Board board = boardRepository.findById(boardId);

        if(board == null) {
            System.out.println("No board found with ID: " + boardId);
            return null;
        }

        TrelloList list = new TrelloList(listId, listName);
        board.addList(list);
        return list;
    }

    public void deleteList(String boardId, String listId) {
        Board board = boardRepository.findById(boardId);
        TrelloList targetList = null;
        for (TrelloList list : board.getLists()) {
            if (list.getListId().equals(listId)) {
                targetList = list;
                break;
            }
        }
        if (targetList != null) {
            board.removeList(targetList);
        }
    }

    public TrelloList getList(String boardId, String listId) {
        Board board = boardRepository.findById(boardId);
        for (TrelloList list : board.getLists()) {
            if (list.getListId().equals(listId)) {
                return list;
            }
        }
        return null;
    }
}
