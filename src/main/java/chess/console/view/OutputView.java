package chess.console.view;

import chess.domain.Color;
import chess.domain.Position;
import chess.dto.PlayerDto;
import chess.dto.PlayersDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {

    public void printGameGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(PlayersDto playersDto) {
        Map<Position, String> chessBoard = new HashMap<>();

        Map<Color, PlayerDto> playerDtos = playersDto.getPlayerDtos();
        for (Entry<Color, PlayerDto> entry : playerDtos.entrySet()) {
            Color color = entry.getKey();
            PlayerDto playerDto = entry.getValue();

            chessBoard.putAll(playerDto.getPieces()
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Entry::getKey,
                            entry1 -> color.convertCase(entry1.getValue())
                    )));
        }

        for (char i = '8'; i >= '1'; i--) {
            for (char j = 'a'; j <= 'h'; j++) {
                Position position = Position.of(j, i);
                System.out.print(piecePrintName(chessBoard, position));
            }
            System.out.println();
        }
    }

    private String piecePrintName(Map<Position, String> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            return chessBoard.get(position);
        }
        return ".";
    }

    public void printPlayerScores(Map<Color, Double> playerScores) {
        for (Color color : playerScores.keySet()) {
            printScoreStatus(color, playerScores.get(color));
        }
    }

    private void printScoreStatus(Color color, Double score) {
        System.out.println(color + "의 점수는 " + score + "입니다.");
    }

    public void printPromotionGuide() {
        System.out.println("폰이 프로모션에 도착했습니다. 프로모션을 진행해주세요.");
        System.out.println("Q, R, B, N 중 하나를 입력해주세요.");
    }
}
