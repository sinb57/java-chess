package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Supplier;

public enum Promotion {

    QUEEN("Q", Queen::getInstance),
    ROOK("R", Rook::getInstance),
    BISHOP("B", Bishop::getInstance),
    KNIGHT("N", Knight::getInstance),
    ;

    private final String value;
    private final Supplier<Piece> createPieceSupplier;

    Promotion(String value, Supplier<Piece> createPieceSupplier) {
        this.value = value;
        this.createPieceSupplier = createPieceSupplier;
    }

    public static Piece createPromotionPiece(String input) {
        return Arrays.stream(values())
                .filter(promotion -> promotion.value.equals(input))
                .map(promotion -> promotion.createPieceSupplier.get())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 프로모션 기물 이름입니다."));
    }
}
