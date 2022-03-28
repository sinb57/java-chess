package chess.domain;

import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.PlayerFactory;
import chess.domain.player.ScoreCalculator;

public class ChessBoard {

    private final Map<Color, Player> players;

    public ChessBoard(Map<Color, Player> players) {
        this.players = players;
    }

    public static ChessBoard initializeChessBoard(PlayerFactory playerFactory) {
        return new ChessBoard(playerFactory.createPlayers());
    }

    public void move(Position source, Position target, Color color) {
        validateFinishedGame();
        validatePlayerOwnPiece(source, color);

        Player player = players.get(color);

        if (isOtherPlayerPiece(target, color)) {
            Player other = findPlayerContainingPosition(target, color);
            player.attack(source, target, other);
            return;
        }
        player.move(source, target);
    }

    private Player findPlayerContainingPosition(Position position, Color color) {
        return players.entrySet()
                .stream()
                .filter(entry -> !color.equals(entry.getKey()))
                .filter(entry -> entry.getValue().contains(position))
                .map(Entry::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalStateException());
    }

    private void validateFinishedGame() {
        if (isFinished()) {
            throw new IllegalStateException("게임이 종료되어 기물을 움직일 수 없습니다.");
        }
    }

    private void validatePlayerOwnPiece(Position position, Color color) {
        if (isOtherPlayerPiece(position, color)) {
            throw new IllegalStateException("상대 진영의 기물을 움직일 수 없습니다.");
        }
    }

    private boolean isOtherPlayerPiece(Position position, Color color) {
        return players.entrySet()
                .stream()
                .filter(entry -> !color.equals(entry.getKey()))
                .anyMatch(entry -> entry.getValue().contains(position));
    }

    public void promotion(Piece piece, Color color) {
        Player player = players.get(color);
        player.promotion(piece);
    }

    public boolean isPositionEmpty(Position position) {
        return players.values()
                .stream()
                .noneMatch(player -> player.contains(position));
    }

    public Piece pieceByPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new NoSuchElementException("해당 위치에 존재하는 기물이 없습니다.");
        }
        return pieces.get(position);
    }

    public Map<Color, Double> calculateScoreStatus() {
        return players.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().calculateScore(new ScoreCalculator())
                ));
    }

    public boolean isFinished() {
        return players.values()
                .stream()
                .anyMatch(Player::isKingDead);
    }

    public boolean isPromotionStatus(Color color) {
        Player player = players.get(color);
        return player.isPromotional();
    }
}
