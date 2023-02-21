package kata.game.framework.models;

import lombok.Getter;
import lombok.NonNull;

public record Coordinates(@NonNull Integer row, @NonNull Integer column) {
}
