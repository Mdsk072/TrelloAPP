package repository;

import entity.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardRepository {
    private Map<String, Board> boards = new HashMap<>();

    public void save(Board board) {
        boards.put(board.getBoardId(), board);
    }

    public Board findById(String boardId) {
        return boards.get(boardId);
    }

    public void delete(String boardId) {
        boards.remove(boardId);
    }

    public List<Board> findAll() {
        return new ArrayList<>(boards.values());
    }
}
