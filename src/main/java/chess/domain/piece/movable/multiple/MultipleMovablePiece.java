package chess.domain.piece.movable.multiple;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public abstract class MultipleMovablePiece extends AbstractPiece {

    protected MultipleMovablePiece(final PieceName pieceName,
                                   final PieceScore pieceScore,
                                   final PieceDirections pieceDirections) {
        super(pieceName, pieceScore, pieceDirections);
    }

    @Override
    protected final boolean isRouteSizeEnoughToMove(int routeSize) {
        return true;
    }

    @Override
    protected final boolean isRouteSizeEnoughToAttack(int routeSize) {
        return true;
    }

    @Override
    public final Piece move() {
        return this;
    }
}
