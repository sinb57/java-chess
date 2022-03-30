package chess.domain.player;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ScoreCalculator {

    private static final double PAWN_DISCOUNT_SCORE = 0.5;

    public double calculateScore(Map<Position, Piece> pieces) {
        return calculateColorDefaultScore(pieces) - PAWN_DISCOUNT_SCORE * countDuplicatedColumnPawn(pieces);
    }

    private double calculateColorDefaultScore(Map<Position, Piece> pieces) {
        return pieces.values()
                .stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private int countDuplicatedColumnPawn(Map<Position, Piece> pieces) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> existPawnInSameColumn(pieces, entry.getKey()))
                .count();
    }

    private boolean existPawnInSameColumn(Map<Position, Piece> pieces, Position position) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isPawn())
                .anyMatch(entry -> existOtherPawnInColumn(position, entry.getKey()));
    }

    private boolean existOtherPawnInColumn(Position position, Position comparePosition) {
        return !position.equals(comparePosition) && position.equalsColumn(comparePosition);
    }
}
