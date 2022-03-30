package chess.console;

import chess.console.view.InputView;
import chess.console.view.OutputView;

public class MainApplication {

    public static void main(String[] args) {
        MainApplication application = new MainApplication();
        application.run();
    }

    private void run() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessApplication application = new ChessApplication(inputView, outputView);
        application.run();
    }
}
