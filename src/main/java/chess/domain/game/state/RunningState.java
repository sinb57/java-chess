package chess.domain.game.state;

import chess.domain.Color;
import chess.domain.player.Players;
import chess.domain.player.ScoreCalculator;
import java.util.Map;

public abstract class RunningState implements GameState {

    protected final Players players;
    protected final Color color;

    protected RunningState(Players players, Color color) {
        this.players = players;
        this.color = color;
    }

    @Override
    public final boolean isRunning() {
        return true;
    }

    public abstract boolean isMovable();

    public abstract boolean isPromotable();

    public final Map<Color, Double> getPlayerScores(ScoreCalculator scoreCalculator) {
        return players.calculatePlayerScores(scoreCalculator);
    }

    public final Players getPlayers() {
        return players;
    }
}
