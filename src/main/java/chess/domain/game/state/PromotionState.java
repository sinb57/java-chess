package chess.domain.game.state;

import chess.domain.Color;
import chess.domain.player.Players;

public final class PromotionState extends RunningState {

    public PromotionState(Players players, Color color) {
        super(players, color);
    }

    public MoveState run(String pieceInitial) {
        players.promotion(color, pieceInitial);
        return new MoveState(players, color.reverse());
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean isPromotable() {
        return true;
    }
}
