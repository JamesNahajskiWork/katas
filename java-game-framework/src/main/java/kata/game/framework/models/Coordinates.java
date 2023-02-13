package kata.game.framework.models;

import lombok.Data;

@Data
public class Coordinates<T> {
    private int row, column;
    private T contents;
}
