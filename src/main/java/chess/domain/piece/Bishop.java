package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.direction.Direction;
import java.util.List;

public final class Bishop extends AbstractPiece {

    private static final String NAME = "B";
    private static final double SCORE = 3;
    private static final List<Direction> DIRECTIONS_TO_MOVE = List.of(
            UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    private static final Bishop INSTANCE = new Bishop();

    private Bishop() {
        super(NAME, SCORE, DIRECTIONS_TO_MOVE);
    }

    public static Bishop getInstance() {
        return INSTANCE;
    }

    @Override
    public Piece move() {
        return this;
    }

    @Override
    protected boolean isRouteSizeEnoughToMove(int routeSize) {
        return true;
    }

    @Override
    protected boolean isRouteSizeEnoughToAttack(int routeSize) {
        return true;
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
