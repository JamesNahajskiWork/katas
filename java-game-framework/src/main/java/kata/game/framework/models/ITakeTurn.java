package kata.game.framework.models;

public interface ITakeTurn<T> {
    void takeTurn(Board<T> boardStateBefore);

    String exceptionLogging();
}
