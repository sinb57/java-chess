package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.direction.Direction;
import java.util.List;

public final class King extends AbstractPiece {

    private static final String NAME = "K";
    private static final double SCORE = 0;
    private static final int MAXIMUM_SIZE_OF_MOVABLE_ROUTE = 1;
    private static final List<Direction> DIRECTIONS_TO_MOVE = List.of(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    private static final King INSTANCE = new King();

    private King() {
        super(NAME, SCORE, DIRECTIONS_TO_MOVE);
    }

    public static King getInstance() {
        return INSTANCE;
    }

    @Override
    public Piece move() {
        return this;
    }

    @Override
    protected boolean isRouteSizeEnoughToMove(int routeSize) {
        return routeSize <= MAXIMUM_SIZE_OF_MOVABLE_ROUTE;
    }

    @Override
    protected boolean isRouteSizeEnoughToAttack(int routeSize) {
        return routeSize <= MAXIMUM_SIZE_OF_MOVABLE_ROUTE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
