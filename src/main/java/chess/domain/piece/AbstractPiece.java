package chess.domain.piece;

import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractPiece implements Piece {

    private final String name;
    private final double score;
    private final List<Direction> directionsToMove;
    private final List<Direction> directionsToAttack;

    protected AbstractPiece(String name, double score, List<Direction> directions) {
        this(name, score, directions, directions);
    }

    protected AbstractPiece(String name, double score,
                            List<Direction> directionsToMove,
                            List<Direction> directionsToAttack) {
        this.name = name;
        this.score = score;
        this.directionsToMove = directionsToMove;
        this.directionsToAttack = directionsToAttack;
    }

    @Override
    public final List<Position> calculateRouteToMove(Position source, Position target) {
        return calculateRoute(directionsToMove, source, target, this::isRouteSizeEnoughToMove);
    }

    @Override
    public final List<Position> calculateRouteToAttack(Position source, Position target) {
        return calculateRoute(directionsToAttack, source, target, this::isRouteSizeEnoughToAttack);
    }

    private List<Position> calculateRoute(List<Direction> directions,
                                          Position source, Position target,
                                          Predicate<Integer> sizeCheckPredicate) {
        return directions.stream()
                .map(direction -> direction.route(source, target))
                .filter(route -> !route.isEmpty())
                .filter(route -> sizeCheckPredicate.test(route.size()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동 가능한 목적지가 아닙니다."));
    }

    protected abstract boolean isRouteSizeEnoughToMove(int routeSize);

    protected abstract boolean isRouteSizeEnoughToAttack(int routeSize);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getScore() {
        return score;
    }
}
