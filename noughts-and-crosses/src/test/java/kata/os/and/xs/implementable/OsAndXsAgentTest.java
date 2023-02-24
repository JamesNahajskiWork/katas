package kata.os.and.xs.implementable;

import kata.game.framework.models.Board;
import kata.os.and.xs.interfaces.OsAndXsAgent;
import kata.os.and.xs.interfaces.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OsAndXsAgentTest {

    private OsAndXsAgent underTest;
    @Mock
    OsAndXsTools osAndXsTools;

    public OsAndXsAgentTest (){
//        underTest = new JamesOsAndXsAgent(Player.O, osAndXsTools);
    }


    @Test
    void lostGame() {
        underTest = new JamesOsAndXsAgent(osAndXsTools);
        Board<Player> mockBoard = new Board<>();
        when(osAndXsTools.checkIfGameIsOver(mockBoard)).thenReturn(Optional.of(Player.X));
        Double calculatedScore = underTest.evaluateBoardState(mockBoard);
        assertEquals(0, calculatedScore);
    }

    @Test
    void wonGame() {
        underTest = new JamesOsAndXsAgent(osAndXsTools);
        Board<Player> mockBoard = new Board<>();
        when(osAndXsTools.checkIfGameIsOver(mockBoard)).thenReturn(Optional.of(Player.O));
        Double calculatedScore = underTest.evaluateBoardState(mockBoard);
        assertEquals(1, calculatedScore);
    }

    @Test
    void drawnGame() {
        underTest = new JamesOsAndXsAgent(osAndXsTools);
        Board<Player> mockBoard = new Board<>();
        when(osAndXsTools.checkIfGameIsOver(mockBoard)).thenReturn(Optional.of(Player.DRAW));
        Double calculatedScore = underTest.evaluateBoardState(mockBoard);
        assertEquals(0.5, calculatedScore);
    }

    @Test
    void ongoingGame() {
        underTest = new JamesOsAndXsAgent(osAndXsTools);
        Board<Player> mockBoard = new Board<>();
        when(osAndXsTools.checkIfGameIsOver(mockBoard)).thenReturn(Optional.empty());
        Double calculatedScore = underTest.evaluateBoardState(mockBoard);
        assertEquals(-1, calculatedScore);
    }
}