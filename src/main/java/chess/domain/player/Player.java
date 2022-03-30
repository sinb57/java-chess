package chess.domain.player;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Player {

    private final Color color;
    private final Map<Position, Piece> pieces;

    public Player(Color color, Map<Position, Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public List<Position> calculateRouteToMove(Position source, Position target) {
        Piece piece = findPieceByPosition(source);
        return piece.calculateRouteToMove(source, target);
    }

    public List<Position> calculateRouteToAttack(Position source, Position target) {
        Piece piece = findPieceByPosition(source);
        return piece.calculateRouteToAttack(source, target);
    }

    public void move(Position source, Position target) {
        validatePositionEmpty(target);
        Piece piece = findPieceByPosition(source).move();
        pieces.remove(source);
        pieces.put(target, piece);
    }

    public void attack(Position source, Position target, Player enemyPlayer) {
        validatePositionEmpty(target);
        enemyPlayer.removePiece(target);
        move(source, target);
    }

    private void removePiece(Position target) {
        validatePieceExist(target);
        pieces.remove(target);
    }

    private void validatePositionEmpty(Position position) {
        if (contains(position)) {
            throw new IllegalStateException();
        }
    }

    public boolean contains(Position position) {
        return pieces.containsKey(position);
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

    public boolean isPromotionAvailable() {
        return pieces.entrySet()
                .stream()
                .anyMatch(entry -> isPawnAndPromotable(entry.getValue(), entry.getKey()));
    }

    private boolean isPawnAndPromotable(Piece piece, Position position) {
        return piece.isPawn() && position.isEndOfColumns();
    }

    public void promotion(Piece piece) {
        Position position = findPositionOfPromotablePawn();
        pieces.put(position, piece);
    }

    public Position findPositionOfPromotablePawn() {
        return pieces.entrySet()
                .stream()
                .filter(entry -> isPawnAndPromotable(entry.getValue(), entry.getKey()))
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("프로모션 가능한 폰이 없습니다."));
    }

    public boolean isSameColor(Color color) {
        return color.equals(this.color);
    }

    public boolean isKingAlive() {
        return pieces.values()
                .stream()
                .anyMatch(Piece::isKing);
    }

    public double calculateScore(ScoreCalculator scoreCalculator) {
        return scoreCalculator.calculateScore(pieces);
    }

    public Color getColor() {
        return color;
    }

    public Map<Position, String> getPieces() {
        return pieces.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().getName()
                ));
    }
}
