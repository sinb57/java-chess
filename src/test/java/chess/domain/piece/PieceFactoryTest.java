package chess.domain.piece;

import chess.domain.player.PlayerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    @DisplayName("새로운 기물 목록 생성")
    void createNewPieces() {
        assertThat(PlayerFactory.createNewChessBoard()).hasSize(32);
    }
}
