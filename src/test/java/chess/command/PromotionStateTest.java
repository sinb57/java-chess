package chess.command;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;

import chess.domain.Position;
import chess.domain.game.state.PromotionState;
import chess.domain.piece.King;
import chess.domain.piece.WhitePawn;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionStateTest {

    @Test
    @DisplayName("프로모션 진행 후 반대 색상으로 넘어간다.")
    void runPromotion() {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                Position.of('a', '8'), new WhitePawn(),
                Position.of('a', '1'), new King(WHITE),
                Position.of('a', '2'), new King(BLACK)
        ));
        PromotionState promotionState = new PromotionState(chessBoard, WHITE);

        assertThat(promotionState.run("Q").color()).isEqualTo(BLACK);
    }
}
