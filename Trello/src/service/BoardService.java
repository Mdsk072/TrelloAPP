package service;

import entity.Board;
import entity.User;
import enums.Privacy;
import repository.BoardRepository;

import java.util.List;

public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(String boardId, String name) {
        Board board = new Board(boardId, name);
        boardRepository.save(board);
        return board;
    }

    public void deleteBoard(String boardId) {
        boardRepository.delete(boardId);
    }

    public void addMemberToBoard(String boardId, User user) {
        Board board = boardRepository.findById(boardId);
        board.addMember(user);
    }

    public void removeMemberFromBoard(String boardId, User user) {
        Board board = boardRepository.findById(boardId);
        board.removeMember(user);
    }

    public Board getBoard(String boardId) {
        return boardRepository.findById(boardId);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public void modifyBoard(Board board, String newName, Privacy newPrivacy) {
        board.setName(newName);
        board.setPrivacy(newPrivacy);
        boardRepository.save(board);
    }
    public Board findBoardById(String boardId) {
        return boardRepository.findById(boardId);
    }
}
