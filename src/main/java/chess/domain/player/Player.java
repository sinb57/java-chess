package chess.domain.player;

import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import chess.domain.Position;
import chess.domain.piece.Piece;

public class Player {

    private static final double PAWN_DISCOUNT_SCORE = 0.5;

    private final Map<Position, Piece> pieces;

    public Player(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean contains(Position position) {
        return pieces.containsKey(position);
    }

    public void move(Position source, Position target) {
        validatePositionEmpty(target);
        Piece piece = findPieceByPosition(source);
        if (!piece.isMovable(source, target)) {
            throw new IllegalStateException();
        }
        pieces.remove(source);
        pieces.put(target, piece);
    }

    public void attack(Position source, Position target, Player other) {
        validatePositionEmpty(target);
        Piece piece = findPieceByPosition(source);
        if (!piece.isAttackable(source, target)) {
            throw new IllegalStateException();
        }
        pieces.remove(source);
        pieces.put(target, piece);
        other.remove(target);
    }

    private void remove(Position position) {
        pieces.remove(position);
    }

    private Piece findPieceByPosition(Position position) {
        validatePieceExist(position);
        return pieces.get(position);
    }

    private void validatePieceExist(Position position) {
        if (!contains(position)) {
            throw new NoSuchElementException("위치에 해당하는 기물을 찾을 수 없습니다.");
        }
    }

    private void validatePositionEmpty(Position position) {
        if (contains(position)) {
            throw new IllegalStateException();
        }
    }

    public void promotion(Piece piece) {
        Position position = pieces.entrySet()
                .stream()
                .filter(entry -> isPromotional(entry.getKey(), entry.getValue()))
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("프로모션 가능한 기물이 없습니다."));
        pieces.put(position, piece);
    }

    private boolean isPromotional(Position position, Piece piece) {
        return piece.isPawn() && position.isPromotionPosition();
    }

    public boolean isPromotional() {
        return pieces.entrySet()
                .stream()
                .anyMatch(entry -> isPromotional(entry.getKey(), entry.getValue()));
    }

    public boolean isKingDead() {
        return !isKingAlive();
    }

    public boolean isKingAlive() {
        return pieces.values()
                .stream()
                .anyMatch(Piece::isKing);
    }

    public double calculateScore(ScoreCalculator scoreCalculator) {
        return scoreCalculator.calculateScore(pieces);
    }
}
