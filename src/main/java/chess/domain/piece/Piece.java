package chess.domain.piece;

import chess.domain.Position;
import java.util.List;

public interface Piece {

    Piece move();

    List<Position> calculateRouteToMove(Position source, Position target);

    List<Position> calculateRouteToAttack(Position source, Position target);

    boolean isPawn();

    boolean isKing();

    String getName();

    double getScore();
}
