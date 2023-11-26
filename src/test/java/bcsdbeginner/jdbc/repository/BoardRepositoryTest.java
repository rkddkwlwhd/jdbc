package bcsdbeginner.jdbc.repository;

import bcsdbeginner.jdbc.Domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class BoardRepositoryTest {
    BoardRepository boardRepository = new BoardRepository();

    @Test
    void createboard() throws SQLException {
        Board board = new Board(1,1,1,"Frist Title","create", LocalDateTime.now());
        Board boardId = boardRepository.createboard(board);
        assertThat(boardId.getUser_id()).isEqualTo(1);
    }

    @Test
    void findById() throws SQLException {
        Board findBoard = boardRepository.findById(1);
        log.info("findBoard = {}", findBoard);
        assertThat(findBoard.getId()).isEqualTo(1);
    }

    @Test
    void updateTitle() throws SQLException {
        boardRepository.updateTitle(1, "update Title");
        Board updateBoard = boardRepository.findById(1);
        log.info("updateTitle = {}", updateBoard);
        assertThat(updateBoard.getTitle()).isEqualTo("update Title");
    }

    @Test
    void deleteBoard() throws SQLException {
        boardRepository.deleteBoard(1);
        Board deleteBoard = boardRepository.findById(1);
        log.info("deleteBoard = {}", deleteBoard);
        assertThat(deleteBoard.getId()).isNull();
    }
}