package chess.command;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import chess.domain.game.state.FinishedState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.MoveState;
import chess.domain.game.state.PromotionState;
import chess.domain.piece.King;
import chess.domain.piece.WhitePawn;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoveStateTest {

    private GameState running;

    @BeforeEach
    void setUp() {
        running = new MoveState(ChessBoard.createNewChessBoard(), WHITE);

    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "move1", "end1"})
    @DisplayName("현재 상태에서 진행 가능한 커맨드가 아니면 예외발생")
    void runException(String inputLine) {
        assertThatThrownBy(() -> running.run(inputLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 진행상태에서 불가능한 명령어입니다.");
    }

    @Test
    @DisplayName("move커맨드 입력 시 다른 색상 상태로 변경")
    void whiteRunningMove() {
        MoveState command = (MoveState) running.run("move a2 a4");
        assertThat(command.color()).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("end커맨드 입력 시 End 상태로 변경")
    void runToEnd() {
        GameState gameState = running.run("end");
        assertThat(gameState).isInstanceOf(FinishedState.class);
    }

    @Test
    @DisplayName("move커맨드 진행 후 promotion이 가능하면 promotion상태로 변경")
    void moveToPromotion() {
        GameState gameState = new MoveState(new ChessBoard(Map.of(
                Position.of('a', '7'), new WhitePawn(),
                Position.of('a', '1'), new King(WHITE),
                Position.of('a', '2'), new King(BLACK))), WHITE);
        assertThat(gameState.run("move a7 a8")).isInstanceOf(PromotionState.class);
    }
}
