import java.util.Scanner;

public class TwoPlayerController {
    private IsolaBoard board;
    private BoardSpace currentPlayer;

    public static void main(String[] args) {
        TwoPlayerController controller = new TwoPlayerController();
        controller.go();
    }

    public void go() {
        // Create board
        board = new IsolaBoard();

        // Create a view attached to that board
        GameView view = new GameView(board);
        currentPlayer = BoardSpace.Player1;

        // Create FileHandler
        FileHandler fileHandler = new FileHandler();

        // Create output.dat
        fileHandler.createNewSaveFile();

        // while game is not over
        while (board.checkWinner() == BoardSpace.Available) {
            // display board
            view.displayBoard();

            // ask current player for their move
            view.askForMove(currentPlayer);
            Scanner scan = new Scanner(System.in);
            String moveAsDirection = scan.nextLine();

            // write move to output.dat
            fileHandler.saveMove(moveAsDirection);

            // make that move on the board
            BoardPosition move = fileHandler.convertToPosition(moveAsDirection, board, currentPlayer);
            board.movePlayer(currentPlayer, move);

            // switch to the next player
            if (currentPlayer == BoardSpace.Player1) {
                currentPlayer = BoardSpace.Player2;
            } else {
                currentPlayer = BoardSpace.Player1;
            }
        }

        // display board
        view.displayBoard();

        // display winner
        view.displayWinner(currentPlayer);

        // close output.dat
        fileHandler.closeWriter();
    }
}
