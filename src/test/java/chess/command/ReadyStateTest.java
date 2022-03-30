package chess.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.state.GameState;
import chess.domain.game.state.MoveState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReadyStateTest {

    @ParameterizedTest
    @ValueSource(strings = {"move", "end", "start1"})
    @DisplayName("게임 시작 커맨드가 아니면 예외발생")
    void runException(String inputLine) {
        GameState gameState = new ReadyState();
        assertThatThrownBy(() -> gameState.run(inputLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않아 다른 명령을 실행할 수 없습니다.");
    }

    @Test
    @DisplayName("커맨드가 null일 경우 예외발생")
    void commandNullException() {
        GameState gameState = new ReadyState();
        assertThatThrownBy(() -> gameState.run(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("command는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("run 실행 시 Running상태로 변경")
    void changeStateByRun() {
        GameState gameState = new ReadyState().run("start");
        assertThat(gameState).isInstanceOf(MoveState.class);
    }
}
