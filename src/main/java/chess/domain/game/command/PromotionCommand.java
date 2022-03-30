package chess.domain.game.command;

import chess.domain.game.state.GameState;
import chess.domain.game.state.PromotionState;
import chess.domain.game.state.RunningState;

public class PromotionCommand implements Command {

    private final PromotionState promotionState;
    private final String pieceInitial;

    private PromotionCommand(PromotionState promotionState, String pieceInitial) {
        this.promotionState = promotionState;
        this.pieceInitial = pieceInitial;
    }

    public static PromotionCommand of(RunningState runningState, String pieceInitial) {
        PromotionState promotionState = convertToPromotionState(runningState);
        return new PromotionCommand(promotionState, pieceInitial);
    }

    private static PromotionState convertToPromotionState(RunningState runningState) {
        validatePromotionState(runningState);
        return (PromotionState) runningState;
    }

    private static void validatePromotionState(RunningState runningState) {
        if (!runningState.isPromotable()) {
            throw new IllegalStateException("프로모션이 가능한 상태가 아닙니다.");
        }
    }

    @Override
    public GameState execute() {
        return promotionState.run(pieceInitial);
    }
}
