package kata.game.framework.models;

public interface ITakeTurn<T extends IPlayer> {
    void takeTurn(Board<T> boardStateBefore);

    String exceptionLogging();
}
