package chess.domain.piece;

import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.direction.Direction;
import java.util.List;

public final class WhitePawn extends Pawn {

    private static final List<Direction> DIRECTIONS_TO_MOVE = List.of(UP);
    private static final List<Direction> DIRECTIONS_TO_ATTACK = List.of(UP_RIGHT, UP_LEFT);
    private static final WhitePawn INSTANCE = new WhitePawn();

    private WhitePawn() {
        super(DIRECTIONS_TO_MOVE, DIRECTIONS_TO_ATTACK);
    }

    public static WhitePawn getInstance() {
        return INSTANCE;
    }
}
