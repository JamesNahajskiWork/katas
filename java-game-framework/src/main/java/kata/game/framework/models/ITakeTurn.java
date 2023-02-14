package kata.game.framework.models;

import java.util.List;

public interface ITakeTurn<T> {
    public Coordinates getNewPosition();
    public T getValue();

    void makeMove(Board<T> boardStateBefore);
}
