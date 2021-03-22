package chess.domain;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

public final class GameResult {

    private final Score whiteTeamScore;
    private final Score blackTeamScore;

    public GameResult(Score whiteTeamScore, Score blackTeamScore) {
        this.whiteTeamScore = whiteTeamScore;
        this.blackTeamScore = blackTeamScore;
    }

    public Score whiteTeamScore() {
        return whiteTeamScore;
    }

    public Score blackTeamScore() {
        return blackTeamScore;
    }

    public TeamColor winner() {
        if (whiteTeamScore.greaterThan(blackTeamScore)) {
            return WHITE;
        }
        return BLACK;
    }
}