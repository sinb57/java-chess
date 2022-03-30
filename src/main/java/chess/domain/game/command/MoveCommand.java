package chess.domain.game.command;

import chess.domain.Position;
import chess.domain.game.state.GameState;
import chess.domain.game.state.MoveState;
import chess.domain.game.state.RunningState;

public class MoveCommand implements Command {

    private final MoveState moveState;
    private final Position source;
    private final Position target;

    private MoveCommand(MoveState moveState, Position source, Position target) {
        this.moveState = moveState;
        this.source = source;
        this.target = target;
    }

    public static MoveCommand of(RunningState runningState, Position source, Position target) {
        MoveState moveState = convertToMoveState(runningState);
        return new MoveCommand(moveState, source, target);
    }

    private static MoveState convertToMoveState(RunningState runningState) {
        validateMoveState(runningState);
        return (MoveState) runningState;
    }

    private static void validateMoveState(RunningState runningState) {
        if (!runningState.isMovable()) {
            throw new IllegalStateException("기물을 움직일 수 있는 상태가 아닙니다.");
        }
    }

    @Override
    public GameState execute() {
        return moveState.run(source, target);
    }
}
