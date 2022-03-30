package chess.console.view;

import camp.nextstep.edu.missionutils.Console;
import chess.console.view.dto.CommandRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String COMMAND_DELIMITER = " ";
    private static final int LIMIT_FOR_SPLIT_ALL_ELEMENT = -1;

    public CommandRequest requestCommand() {
        String inputLine = Console.readLine();
        List<String> inputValues = splitInputLineWithDelimiter(inputLine);
        return convertToCommandRequest(inputValues);
    }

    private List<String> splitInputLineWithDelimiter(String inputLine) {
        return Arrays.stream(inputLine.split(COMMAND_DELIMITER, LIMIT_FOR_SPLIT_ALL_ELEMENT))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private CommandRequest convertToCommandRequest(List<String> inputValues) {
        validateInputValuesNotEmpty(inputValues);
        String command = inputValues.remove(0);
        return new CommandRequest(command, inputValues);
    }

    private static void validateInputValuesNotEmpty(List<String> inputValues) {
        if (inputValues.isEmpty()) {
            throw new IllegalArgumentException("명령어가 비어있습니다.");
        }
    }

    public String requestPieceInitialToPromotion() {
        String inputLine = Console.readLine();
        return inputLine.trim();
    }
}
