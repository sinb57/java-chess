package chess.domain.piece;

import chess.domain.direction.Direction;
import java.util.List;

public class Pawn extends AbstractPiece {

    private static final String NAME = "P";
    private static final double SCORE = 1;

    private static final int FIRST_MOVABLE_COUNT = 2;
    private static final int NORMAL_MOVABLE_COUNT = 1;
    private static final int MOVABLE_COUNT_TO_ATTACK = 1;

    private final List<Direction> directionsToMove;
    private final List<Direction> directionsToAttack;
    private final int maximumCountToMove;

    protected Pawn(List<Direction> directionsToMove, List<Direction> directionsToAttack) {
        this(FIRST_MOVABLE_COUNT, directionsToMove, directionsToAttack);
    }

    private Pawn(int movableCount, List<Direction> directionsToMove, List<Direction> directionsToAttack) {
        super(NAME, SCORE, directionsToMove, directionsToAttack);
        this.maximumCountToMove = movableCount;
        this.directionsToMove = directionsToMove;
        this.directionsToAttack = directionsToAttack;
    }

    @Override
    public Piece move() {
        return new Pawn(NORMAL_MOVABLE_COUNT, directionsToMove, directionsToAttack);
    }

    @Override
    protected boolean isRouteSizeEnoughToMove(int routeSize) {
        return routeSize <= maximumCountToMove;
    }

    @Override
    protected boolean isRouteSizeEnoughToAttack(int routeSize) {
        return routeSize <= MOVABLE_COUNT_TO_ATTACK;
    }

    @Override
    public final boolean isPawn() {
        return true;
    }

    @Override
    public final boolean isKing() {
        return false;
    }
}
