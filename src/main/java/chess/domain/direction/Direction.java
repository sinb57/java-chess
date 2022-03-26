package chess.domain.direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.domain.Position;

public enum Direction {

    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    UP_UP_RIGHT(1, 2),
    UP_RIGHT_RIGHT(2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),

    UP_UP_LEFT(-1, 2),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),
    ;

    private final int columnAmount;
    private final int rowAmount;

    Direction(int columnAmount, int rowAmount) {
        this.columnAmount = columnAmount;
        this.rowAmount = rowAmount;
    }

    public Position move(Position position) {
        return position.move(columnAmount, rowAmount);
    }

    public List<Position> route(Position source, Position target) {
        if (isNotMovable(source, target)) {
            return Collections.emptyList();
        }
        return calculateRoute(source, target, new ArrayList<>());
    }

    private boolean isNotMovable(Position source, Position target) {
        return source.equals(target) || !isMovableByMultipleMovable(source, target);
    }

    private boolean isMovableByMultipleMovable(Position source, Position target) {
        if (source.equals(target)) {
            return true;
        }
        if (source.isMovable(columnAmount, rowAmount)) {
            return isMovableByMultipleMovable(move(source), target);
        }
        return false;
    }

    private List<Position> calculateRoute(Position source, Position target, List<Position> route) {
        if (source.equals(target)) {
            return route;
        }
        Position movePosition = move(source);
        route.add(movePosition);
        return calculateRoute(movePosition, target, route);
    }
}
