import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReplayController {

    public static void main(String[] args) {
        ReplayController controller = new ReplayController();
        controller.go();
    }

    public void go() {
        // Open output.dat
        File saveGame = new File("output.dat");
        Scanner scan = null;
        try {
            scan = new Scanner(saveGame);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open output.dat");
            return;
        }

        // Create board
        IsolaBoard board = new IsolaBoard();
        // Create associated view
        GameView view = new GameView(board);

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
            BoardSpace currentPlayer = BoardSpace.Player2; // this doesn't feel right

            String moveAsDirection = scan.nextLine();
            BoardPosition move = this.convertToPosition(moveAsDirection);
            board.movePlayer(currentPlayer, move);
            currentPlayer = BoardSpace.Player1; // not sure about this

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
        scan.close();
    }

    private BoardPosition convertToPosition(String moveAsDirection) {
        // Convert the cardinal direction given by the player to a move that can be used by the board.movePlayer method.

        // WIP
        BoardPosition placeholder = new BoardPosition(1, 1);
        return placeholder;

        // Having this method in both the TwoPlayerController and the ReplayController is redundant.
        // Would like to make a 'helper' methods class but unsure of best practices in Java for this.
    }
}
