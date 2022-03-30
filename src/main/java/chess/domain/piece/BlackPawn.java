package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;

import chess.domain.direction.Direction;
import java.util.List;

public final class BlackPawn extends Pawn {

    private static final List<Direction> DIRECTIONS_TO_MOVE = List.of(DOWN);
    private static final List<Direction> DIRECTIONS_TO_ATTACK = List.of(DOWN_RIGHT, DOWN_LEFT);
    private static final BlackPawn INSTANCE = new BlackPawn();

    private BlackPawn() {
        super(DIRECTIONS_TO_MOVE, DIRECTIONS_TO_ATTACK);
    }

    public static BlackPawn getInstance() {
        return INSTANCE;
    }
}
