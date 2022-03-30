package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN_DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.direction.Direction.DOWN_LEFT_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT_RIGHT;
import static chess.domain.direction.Direction.UP_LEFT_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT_RIGHT;
import static chess.domain.direction.Direction.UP_UP_LEFT;
import static chess.domain.direction.Direction.UP_UP_RIGHT;

import chess.domain.direction.Direction;
import java.util.List;

public final class Knight extends AbstractPiece {

    private static final String NAME = "N";
    private static final double SCORE = 2.5;
    private static final int MAXIMUM_SIZE_OF_MOVABLE_ROUTE = 1;
    private static final List<Direction> DIRECTIONS_TO_MOVE = List.of(
            UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT,
            UP_UP_LEFT, UP_LEFT_LEFT, DOWN_DOWN_LEFT, DOWN_LEFT_LEFT);
    private static final Knight INSTANCE = new Knight();

    private Knight() {
        super(NAME, SCORE, DIRECTIONS_TO_MOVE);
    }

    public static Knight getInstance() {
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
        return false;
    }
}
