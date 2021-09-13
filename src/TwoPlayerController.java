import java.util.Arrays;
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
        // Create a view attached to the board
        GameView view = new GameView(board);

        // Create FileHandler
        FileHandler fileHandler = new FileHandler();
        // Create game.dat
        fileHandler.createNewSaveFile();

        // Set currentPlayer to Player 1 at start of new game
        currentPlayer = BoardSpace.Player1;

        // while game is not over
        while (board.checkWinner() == BoardSpace.Available) {
            // display board
            view.displayBoard();

            // ask current player for their move
            view.askForMove(currentPlayer); // this is just output; view isn't handling any input.

            // accept input from player
            Scanner scan = new Scanner(System.in);
            String moveAsCardinal = scan.nextLine().toUpperCase();

            // check to see if the move is in the correct format
            moveAsCardinal = this.checkMoveFormat(moveAsCardinal, view, scan);

            // write move to game.dat
            fileHandler.saveMove(moveAsCardinal);

            // make that move on the board
            BoardPosition move = fileHandler.convertToPosition(moveAsCardinal, board, currentPlayer);
            board.movePlayer(currentPlayer, move);

            // switch to the next player
            switchPlayer();
        }

        // display board
        view.displayBoard();
        // display winner
        view.displayWinner(currentPlayer);

        // close game.dat
        fileHandler.closeWriter();
    }

    private String checkMoveFormat(String moveAsCardinal, GameView view, Scanner scan) {
        String[] validCardinalInputs = {"N", "NE","E", "SE", "S", "SW", "W", "NW"};
        while (! Arrays.asList(validCardinalInputs).contains(moveAsCardinal)) {
            view.tryAgain();
            moveAsCardinal = scan.nextLine().toUpperCase();
        }
        return moveAsCardinal;
    }

    private void switchPlayer() {
        if (currentPlayer == BoardSpace.Player1) {
            currentPlayer = BoardSpace.Player2;
        } else {
            currentPlayer = BoardSpace.Player1;
        }
    }
}
