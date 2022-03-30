package chess.console;

import chess.domain.game.ChessGame;
import java.util.Arrays;
import java.util.List;

public enum CommandExecutor {

    START("START", (chessApplication, chessGame, commandOptions) -> chessApplication.executeStart(chessGame)),
    MOVE("MOVE", ChessApplication::executeMove),
    STATUS("STATUS", (chessApplication, chessGame, commandOptions) -> chessApplication.executeStatus(chessGame)),
    END("END", (chessApplication, chessGame, commandOptions) -> chessApplication.executeEnd(chessGame)),
    ;

    private final String command;
    private final ChessApplicationExecution chessApplicationExecution;

    CommandExecutor(String command, ChessApplicationExecution chessApplicationExecution) {
        this.command = command;
        this.chessApplicationExecution = chessApplicationExecution;
    }

    public static CommandExecutor from(String command) {
        return Arrays.stream(values())
                .filter(it -> command.equalsIgnoreCase(it.command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("명령어를 잘못 입력하셨습니다."));
    }

    public void execute(ChessApplication chessApplication, ChessGame chessGame, List<String> commandOptions) {
        chessApplicationExecution.execute(chessApplication, chessGame, commandOptions);
    }

    @FunctionalInterface
    private interface ChessApplicationExecution {
        void execute(ChessApplication chessApplication, ChessGame chessGame, List<String> commandOptions);
    }
}
