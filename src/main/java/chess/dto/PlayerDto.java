package chess.dto;

import chess.domain.Position;
import java.util.Map;

public class PlayerDto {

    private final Map<Position, String> pieces;

    public PlayerDto(Map<Position, String> pieces) {
        this.pieces = pieces;
    }

    public Map<Position, String> getPieces() {
        return pieces;
    }
}
