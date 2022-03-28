package chess.domain.player;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.single.King;
import chess.domain.piece.single.Knight;

public class PlayerFactory {

    private static final PlayerFactory INSTANCE = new PlayerFactory();

    public static PlayerFactory getInstance() {
        return INSTANCE;
    }

    public Map<Color, Player> createPlayers() {
        Map<Color, Player> players = new EnumMap<>(Color.class);
        players.put(Color.BLACK, createBlackPlayer());
        players.put(Color.WHITE, createWhitePlayer());
        return players;
    }

    private Player createBlackPlayer() {
        Color color = Color.BLACK;
        Map<Position, Piece> blackPieces = new HashMap<>(Map.of(
                Position.of('a', '8'), new Rook(color),
                Position.of('b', '8'), new Knight(color),
                Position.of('c', '8'), new Bishop(color),
                Position.of('d', '8'), new Queen(color),
                Position.of('e', '8'), new King(color),
                Position.of('f', '8'), new Bishop(color),
                Position.of('g', '8'), new Knight(color),
                Position.of('h', '8'), new Rook(color)
        ));
        for (char i = 0; i < 8; i++) {
            blackPieces.put(Position.of((char) ('a' + i), '7'), new BlackPawn());
        }
        return new Player(blackPieces);
    }

    private Player createWhitePlayer() {
        Color color = Color.WHITE;
        Map<Position, Piece> whitePieces = new HashMap<>(Map.of(
                Position.of('a', '1'), new Rook(color),
                Position.of('b', '1'), new Knight(color),
                Position.of('c', '1'), new Bishop(color),
                Position.of('d', '1'), new Queen(color),
                Position.of('e', '1'), new King(color),
                Position.of('f', '1'), new Bishop(color),
                Position.of('g', '1'), new Knight(color),
                Position.of('h', '1'), new Rook(color)
        ));
        for (char i = 0; i < 8; i++) {
            whitePieces.put(Position.of((char) ('a' + i), '2'), new WhitePawn());
        }
        return new Player(whitePieces);
    }
}
