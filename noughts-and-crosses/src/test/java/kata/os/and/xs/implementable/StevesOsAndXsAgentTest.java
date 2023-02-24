package kata.os.and.xs.implementable;

import kata.game.framework.models.Board;
import kata.os.and.xs.interfaces.OsAndXsAgent;
import kata.os.and.xs.interfaces.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StevesOsAndXsAgentTest {

    @Test
    void checkIfIWon(){

        OsAndXsAgent agent = new StevesOsAndXsAgent();

        Board<Player> noughtsAndCrossesBoard = new Board<Player>(3);

        Double aDouble = agent.evaluateBoardState(noughtsAndCrossesBoard);

    }

}