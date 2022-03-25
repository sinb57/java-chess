package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        Objects.requireNonNull(pieces, "pieces는 null이 들어올 수 없습니다.");
        this.pieces = new HashMap<>(pieces);
    }

    public static ChessBoard createNewChessBoard() {
        return new ChessBoard(PieceFactory.createNewChessBoard());
    }

    public void movePiece(Position start, Position target) {
        Piece movedPiece = pieceByPosition(start).move(start, target, this);
        pieces.remove(start);
        pieces.put(start, movedPiece);
    }

    public boolean isPositionEmpty(Position position) {
        return !pieces.containsKey(position);
    }

    public Piece pieceByPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new NoSuchElementException("해당 위치에 존재하는 기물이 없습니다.");
        }
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
