package chess.console.view.dto;

import java.util.List;

public class CommandRequest {

    private final String command;
    private final List<String> options;

    public CommandRequest(String command, List<String> options) {
        this.command = command;
        this.options = options;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getOptions() {
        return options;
    }
}
