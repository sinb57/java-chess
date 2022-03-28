package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.player.PlayerFactory;

class PieceFactoryTest {

    @Test
    @DisplayName("새로운 기물 목록 생성")
    void createNewPieces() {
        assertThat(PlayerFactory.createNewChessBoard()).hasSize(32);
    }
}
