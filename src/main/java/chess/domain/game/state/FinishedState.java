package chess.domain.game.state;

import chess.domain.Color;
import chess.domain.player.Players;

public final class FinishedState implements GameState {

    private final Players players;
    private final Color color;

    public FinishedState(final Players players, final Color color) {
        this.players = players;
        this.color = color;
    }

    @Override
    public GameState end() {
        throw new IllegalStateException("이미 게임은 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Players getPlayers() {
        return players;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
