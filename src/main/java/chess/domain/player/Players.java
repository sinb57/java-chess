package chess.domain.player;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.Promotion;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players initialize(PlayerFactory playerFactory) {
        return new Players(playerFactory.createPlayers());
    }

    private Player findPlayerByColor(Color color) {
        return players.stream()
                .filter(player -> player.isSameColor(color))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 색상의 플레이어를 찾을 수 없습니다."));
    }

    public void move(Color color, Position source, Position target) {
        Player player = findPlayerByColor(color);
        validateOtherPlayersNotPlacedPosition(player, source);

        findEnemyPlayerByPosition(player, target).ifPresentOrElse(
                (enemyPlayer) -> attackPlayer(player, enemyPlayer, source, target),
                () -> movePlayer(player, source, target)
        );
    }

    private void validateOtherPlayersNotPlacedPosition(Player originPlayer, Position position) {
        if (findEnemyPlayerByPosition(originPlayer, position).isPresent()) {
            throw new IllegalStateException("다른 플레이어의 기물은 움직일 수 없습니다.");
        }
    }

    private Optional<Player> findEnemyPlayerByPosition(Player originPlayer, Position position) {
        return players.stream()
                .filter(player -> !player.equals(originPlayer))
                .filter(player -> player.contains(position))
                .findAny();
    }

    private void attackPlayer(Player player, Player enemyPlayer, Position source, Position target) {
        List<Position> routeToAttack = player.calculateRouteToAttack(source, target);
        validateRouteNotBlockedBeforeArrival(routeToAttack);
        player.attack(source, target, enemyPlayer);
    }

    private void movePlayer(Player player, Position source, Position target) {
        List<Position> routeToMove = player.calculateRouteToMove(source, target);
        validateRouteNotBlockedBeforeArrival(routeToMove);
        player.move(source, target);
    }

    private void validateRouteNotBlockedBeforeArrival(List<Position> route) {
        if (isRouteBlockedBeforeArrival(route)) {
            throw new IllegalStateException("이동 경로가 가로막혀 있습니다.");
        }
    }

    private boolean isRouteBlockedBeforeArrival(List<Position> route) {
        List<Position> routeBeforeArrival = route.subList(0, route.size() - 1);
        return routeBeforeArrival.stream()
                .anyMatch(this::isPiecePlacedAtPosition);
    }

    private boolean isPiecePlacedAtPosition(Position position) {
        return players.stream()
                .anyMatch(player -> player.contains(position));
    }

    public boolean isPlayerPromotable(Color color) {
        Player player = findPlayerByColor(color);
        return player.isPromotionAvailable();
    }

    public void promotion(Color color, String pieceInitial) {
        Player player = findPlayerByColor(color);
        Piece promotedPiece = Promotion.createPromotionPiece(pieceInitial);
        player.promotion(promotedPiece);
    }

    public boolean isOnlyOneKingLeft() {
        return countPlayersWithKingAlive() == 1;
    }

    public int countPlayersWithKingAlive() {
        return (int) players.stream()
                .filter(Player::isKingAlive)
                .count();
    }

    public Map<Color, Double> calculatePlayerScores(ScoreCalculator scoreCalculator) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getColor,
                        player -> player.calculateScore(scoreCalculator)
                ));
    }

    public List<Color> getPlayerColors() {
        return players.stream()
                .map(Player::getColor)
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Position, String> getPiecesByPlayer(Color color) {
        return findPlayerByColor(color).getPieces();
    }
}
