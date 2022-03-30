package chess.console;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.console.view.dto.CommandRequest;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.ChessGame;
import chess.dto.PlayersDto;
import java.util.List;
import java.util.Map;

public class ChessApplication {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessApplication(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        outputView.printGameGuide();

        do {
            play(chessGame);
        } while (chessGame.isRunning());
    }

    private void play(ChessGame chessGame) {
        CommandRequest commandRequest = inputView.requestCommand();
        String command = commandRequest.getCommand();
        List<String> commandOptions = commandRequest.getOptions();

        CommandExecutor commandExecutor = CommandExecutor.from(command);
        commandExecutor.execute(this, chessGame, commandOptions);
    }

    public void executeStart(ChessGame chessGame) {
        chessGame.start();
        outputView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));
    }

    public void executeMove(ChessGame chessGame, List<String> commandOptions) {
        validateMoveCommandOptionSizeEnough(commandOptions);

        Position source = Position.from(commandOptions.get(0));
        Position target = Position.from(commandOptions.get(1));
        chessGame.movePiece(source, target);
        outputView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));

        executePromotionIfPossible(chessGame);
    }

    private void validateMoveCommandOptionSizeEnough(List<String> commandOptions) {
        if (commandOptions.size() != 2) {
            throw new IllegalArgumentException("MOVE 옵션이 잘못되었습니다.");
        }
    }

    private void executePromotionIfPossible(ChessGame chessGame) {
        if (chessGame.isPromotable()) {
            outputView.printPromotionGuide();
            chessGame.promotePiece(inputView.requestPieceInitialToPromotion());
            outputView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));
        }
    }

    public void executeStatus(ChessGame chessGame) {
        outputView.printChessBoard(PlayersDto.toDto(chessGame.getPlayers()));
        Map<Color, Double> playerScores = chessGame.getPlayerScores();
        outputView.printPlayerScores(playerScores);
    }

    public void executeEnd(ChessGame chessGame) {
        chessGame.end();
    }
}
