package kata.game.framework.models;

import lombok.Getter;

@Getter
public class Coordinates {
    private final int row, column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
