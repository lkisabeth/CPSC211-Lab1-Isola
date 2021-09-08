public class ReplayController {
    private IsolaBoard board;
    private BoardSpace currentPlayer;

    public static void main(String[] args) {
        ReplayController controller = new ReplayController();
        controller.go();
    }

    public void go() {
        // Create board
        board = new IsolaBoard();

        // Create associated view
        GameView view = new GameView(board);
        currentPlayer = BoardSpace.Player1; // MAYBE refactor to allow for

        // Create FileHandler
        FileHandler fileHandler = new FileHandler();

        // Open output.dat
        fileHandler.openGameFile();

        // while game is not over
        while (board.checkWinner() == BoardSpace.Available) {
            // display board
            view.displayBoard();

            // read player move from output.dat
            String moveAsDirection = fileHandler.loadNextMove();
            BoardPosition move = fileHandler.convertToPosition(moveAsDirection, board, currentPlayer);

            // make move on board
            board.movePlayer(currentPlayer, move);

            //switch to next player
            if (currentPlayer == BoardSpace.Player1) {
                currentPlayer = BoardSpace.Player2;
            } else {
                currentPlayer = BoardSpace.Player1;
            }

            // delay for 1 second
            int seconds = 1;
            try {
                Thread.sleep(seconds*1000);
            } catch (Exception e) {
                // This should never happen
            }
        }

        // display board
        view.displayBoard();

        // Close output.dat
        fileHandler.closeScanner();
    }
}
