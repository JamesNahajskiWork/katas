package kata.game.framework;

import kata.game.framework.models.IPlayer;

public class GameOverException extends Exception {
    public GameOverException(IPlayer winner) {
        super("Player " + winner + " won");
    }
}
