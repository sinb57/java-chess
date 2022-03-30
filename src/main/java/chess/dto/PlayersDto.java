package chess.dto;

import chess.domain.Color;
import chess.domain.player.Players;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersDto {

    private final Map<Color, PlayerDto> playerDtos;

    public PlayersDto(Map<Color, PlayerDto> playerDtos) {
        this.playerDtos = playerDtos;
    }

    public static PlayersDto toDto(Players players) {
        return new PlayersDto(Arrays.stream(Color.values())
                .collect(Collectors.toMap(
                        color -> color,
                        color -> new PlayerDto(players.getPiecesByPlayer(color)))
                ));
    }

    public Map<Color, PlayerDto> getPlayerDtos() {
        return playerDtos;
    }
}
