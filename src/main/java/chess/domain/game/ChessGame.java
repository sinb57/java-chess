package chess.domain.game;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.PromotionCommand;
import chess.domain.game.state.FinishedState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.MoveState;
import chess.domain.game.state.RunningState;
import chess.domain.player.Players;
import chess.domain.player.ScoreCalculator;
import java.util.Map;

public class ChessGame {

    private GameState gameState;

    public ChessGame() {
        gameState = new FinishedState();
    }

    public void start() {
        validateGameAvailableToStart();
        gameState = MoveState.createFirstTurnRunning();
    }

    private void validateGameAvailableToStart() {
        if (gameState.isRunning()) {
            throw new IllegalStateException("게임이 이미 실행중입니다.");
        }
    }

    public void movePiece(Position source, Position target) {
        RunningState runningState = convertToRunningState(gameState);
        MoveCommand moveCommand = MoveCommand.of(runningState, source, target);
        moveCommand.execute();
    }

    public void promotePiece(String pieceInitial) {
        RunningState runningState = convertToRunningState(gameState);
        PromotionCommand promotionCommand = PromotionCommand.of(runningState, pieceInitial);
        promotionCommand.execute();
    }

    public Map<Color, Double> getPlayerScores() {
        RunningState runningState = convertToRunningState(gameState);
        return runningState.getPlayerScores(new ScoreCalculator());
    }

    public void end() {
        gameState = new FinishedState();
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public boolean isPromotable() {
        RunningState runningState = convertToRunningState(gameState);
        return runningState.isPromotable();
    }

    public Players getPlayers() {
        RunningState runningState = convertToRunningState(gameState);
        return runningState.getPlayers();
    }

    private RunningState convertToRunningState(GameState gameState) {
        validateGameIsRunning();
        return (RunningState) gameState;
    }

    private void validateGameIsRunning() {
        if (!gameState.isRunning()) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
    }
}
