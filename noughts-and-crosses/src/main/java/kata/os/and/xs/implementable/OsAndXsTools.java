package kata.os.and.xs.implementable;

import kata.game.framework.GameOverException;
import kata.game.framework.models.Board;
import kata.game.framework.models.PlayPiece;
import kata.game.framework.service.IGameTools;
import kata.os.and.xs.interfaces.Player;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OsAndXsTools implements IGameTools<Player, PlayPiece<Player>> {

    @Override
    public boolean checkMoveIsValid(PlayPiece<Player> proposedMove, Board<Player> boardState) {
        return boardState.getCellAtCoords(proposedMove.getCoordinates()) == null;
    }

    @Override
    public Optional<Player> checkIfGameIsOver(Board<Player> gameState) {
        int[][] diagDirections = {{1, 1}, {1, -1}};
        for (int[] direction : diagDirections) {
            Player central = gameState.getCell(1, 1);
            Player plus1 = gameState.getCell(1 + direction[0], 1 + direction[1]);
            Player minus1 = gameState.getCell(1 - direction[0], 1 - direction[1]);
            if (central == plus1 && plus1 == minus1 && central != null) {
                return Optional.of(central);
            }
        }
        for (int y = 0; y < 3; y++) {
            Player minus1 = gameState.getCell(y, 1);
            Player central = gameState.getCell(y, 0);
            Player plus1 = gameState.getCell(y, 2);
            if (central == plus1 && plus1 == minus1 && central != null) {
                return Optional.of(central);
            }
        }
        for (int x = 0; x < 3; x++) {
            Player minus1 = gameState.getCell(1, x);
            Player central = gameState.getCell(0, x);
            Player plus1 = gameState.getCell(2, x);
            if (central == plus1 && plus1 == minus1 && central != null) {
                return Optional.of(central);
            }
        }
        if (gameState.isFull()) {
            return Optional.of(Player.DRAW);
        }
        return Optional.empty();
    }
}
