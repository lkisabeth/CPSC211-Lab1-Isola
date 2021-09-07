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
        currentPlayer = BoardSpace.Player1;

        // Create FileHandler
        FileHandler fileHandler = new FileHandler();

        // Open output.dat
        fileHandler.openGameFile();

        // while game is not over
        //      display board
        //
        //      read player move from output.dat
        //      make move on board
        //      switch to next player
        //
        //      delay for 1 second
        while (board.checkWinner() == BoardSpace.Available) {
            view.clearScreen();
            view.displayBoard();

            String moveAsDirection = fileHandler.loadNextMove();
            BoardPosition move = fileHandler.convertToPosition(moveAsDirection, board, currentPlayer);
            board.movePlayer(currentPlayer, move);
            if (currentPlayer == BoardSpace.Player1) {
                currentPlayer = BoardSpace.Player2;
            } else {
                currentPlayer = BoardSpace.Player1;
            }

            int seconds = 1;
            try {
                Thread.sleep(seconds*1000);
            } catch (Exception e) {
                // This should never happen
            }
        }

        // display board
        view.clearScreen();
        view.displayBoard();

        // Close output.dat
        fileHandler.closeScanner();
    }
}
