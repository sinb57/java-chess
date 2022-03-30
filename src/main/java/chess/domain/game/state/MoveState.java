package chess.domain.game.state;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.player.PlayerFactory;
import chess.domain.player.Players;

public final class MoveState extends RunningState {

    public MoveState(Players players, Color color) {
        super(players, color);
    }

    public static MoveState createFirstTurnRunning() {
        Players players = Players.initialize(PlayerFactory.getInstance());
        return new MoveState(players, Color.WHITE);
    }

    public GameState run(Position source, Position target) {
        players.move(color, source, target);
        return considerGameState();
    }

    private GameState considerGameState() {
        if (players.isPlayerPromotable(color)) {
            return new PromotionState(players, color);
        }
        if (players.isOnlyOneKingLeft()) {
            return new FinishedState();
        }
        return new MoveState(players, color.reverse());
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isPromotable() {
        return false;
    }
}
