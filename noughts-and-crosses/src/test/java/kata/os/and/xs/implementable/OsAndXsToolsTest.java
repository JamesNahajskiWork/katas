package kata.os.and.xs.implementable;

import kata.game.framework.GameOverException;
import kata.game.framework.models.Board;
import kata.os.and.xs.interfaces.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OsAndXsToolsTest {

    final OsAndXsTools underTest = new OsAndXsTools();


    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void threeInARow(int row) throws GameOverException {
        Board<Player> board = new Board<>(3);
        board.setCell(0,row,Player.X);
        board.setCell(1,row,Player.X);
        board.setCell(2,row,Player.X);
        assertEquals(Player.X, underTest.checkIfGameIsOver(board).get());
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    void threeInAColumn(int column) throws GameOverException {
        Board<Player> board = new Board<>(3);
        board.setCell(column,0,Player.X);
        board.setCell(column,1,Player.X);
        board.setCell(column,2,Player.X);
        assertEquals(Player.X, underTest.checkIfGameIsOver(board).get());
    }

    @Test
    void downDiag() throws GameOverException {
        Board<Player> board = new Board<>(3);
        board.setCell(0,0,Player.X);
        board.setCell(1,1,Player.X);
        board.setCell(2,2,Player.X);
        assertEquals(Player.X, underTest.checkIfGameIsOver(board).get());
    }

    @Test
    void upDiag() throws GameOverException {
        Board<Player> board = new Board<>(3);
        board.setCell(0,2,Player.X);
        board.setCell(1,1,Player.X);
        board.setCell(2,0,Player.X);
        assertEquals(Player.X, underTest.checkIfGameIsOver(board).get());
    }


}