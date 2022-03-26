package chess.view;

import java.util.Map;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class OutputView {

    private OutputView() {
        throw new AssertionError();
    }

    public static void printGameGuide() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(Map<Position, Piece> pieces) {
        for (char i = '8'; i >= '1'; i--) {
            for (char j = 'a'; j <= 'h'; j++) {
                Position position = Position.of(j, i);
                System.out.print(piecePrintName(pieces, position));
            }
            System.out.println();
        }
    }

    private static String piecePrintName(Map<Position, Piece> pieces, Position position) {
        if (pieces.containsKey(position)) {
            Piece piece = pieces.get(position);
            return piece.convertedName();
        }
        return ".";
    }

    public static void printChessBoardStatus(Map<Color, Double> chessBoardStatus) {
        for (Color color : chessBoardStatus.keySet()) {
            printScoreStatus(color, chessBoardStatus.get(color));
        }
    }

    private static void printScoreStatus(Color color, Double score) {
        System.out.println(color + "의 점수는 " + score + "입니다.");
    }

    public static void printPromotionGuide() {
        System.out.println("폰이 프로모션에 도착했습니다. 프로모션을 진행해주세요.");
        System.out.println("Q, R, B, N 중 하나를 입력해주세요.");
    }
}
