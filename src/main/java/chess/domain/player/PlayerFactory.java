package chess.domain.player;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFactory {

    private static final PlayerFactory INSTANCE = new PlayerFactory();

    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        return INSTANCE;
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(createBlackPlayer());
        players.add(createWhitePlayer());
        return players;
    }

    private Player createBlackPlayer() {
        Color color = BLACK;
        Map<Position, Piece> blackPieces = new HashMap<>(Map.of(
                Position.of('a', '8'), Rook.getInstance(),
                Position.of('b', '8'), Knight.getInstance(),
                Position.of('c', '8'), Bishop.getInstance(),
                Position.of('d', '8'), Queen.getInstance(),
                Position.of('e', '8'), King.getInstance(),
                Position.of('f', '8'), Bishop.getInstance(),
                Position.of('g', '8'), Knight.getInstance(),
                Position.of('h', '8'), Rook.getInstance()
        ));
        for (char i = 0; i < 8; i++) {
            blackPieces.put(Position.of((char) ('a' + i), '7'), BlackPawn.getInstance());
        }
        return new Player(BLACK, blackPieces);
    }

    private Player createWhitePlayer() {
        Color color = WHITE;
        Map<Position, Piece> whitePieces = new HashMap<>(Map.of(
                Position.of('a', '1'), Rook.getInstance(),
                Position.of('b', '1'), Knight.getInstance(),
                Position.of('c', '1'), Bishop.getInstance(),
                Position.of('d', '1'), Queen.getInstance(),
                Position.of('e', '1'), King.getInstance(),
                Position.of('f', '1'), Bishop.getInstance(),
                Position.of('g', '1'), Knight.getInstance(),
                Position.of('h', '1'), Rook.getInstance()
        ));
        for (char i = 0; i < 8; i++) {
            whitePieces.put(Position.of((char) ('a' + i), '2'), WhitePawn.getInstance());
        }
        return new Player(WHITE, whitePieces);
    }
}
