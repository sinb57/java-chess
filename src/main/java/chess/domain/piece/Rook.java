package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;

import chess.domain.direction.Direction;
import java.util.List;

public final class Rook extends AbstractPiece {

    private static final String NAME = "R";
    private static final double SCORE = 5;
    private static final List<Direction> DIRECTIONS_TO_MOVE = List.of(UP, DOWN, RIGHT, LEFT);
    private static final Rook INSTANCE = new Rook();

    private Rook() {
        super(NAME, SCORE, DIRECTIONS_TO_MOVE);
    }

    public static Rook getInstance() {
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
