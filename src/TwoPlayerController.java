import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        this.board = new IsolaBoard();
        // Create a view attached to that board
        GameView view = new GameView(board);
        this.currentPlayer = BoardSpace.Player1; // maybe refactor

        // Create output.dat
        File saveGame = new File("output.dat");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(saveGame);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open output.dat");
            return;
        }

        // while game is not over
        //      display board
        //
        //      ask current player for their move
        //      write move to output.dat
        //      make that move on the board
        //      switch to next player
        while (board.checkWinner() == BoardSpace.Available) {
            view.clearScreen();
            view.displayBoard();

            view.askForMove();
            Scanner scan = new Scanner(System.in);
            String moveAsDirection = scan.nextLine();
            writer.println(moveAsDirection);
            BoardPosition move = this.convertToPosition(moveAsDirection);
            board.movePlayer(currentPlayer, move);
            currentPlayer = BoardSpace.Player2; // might need to change this
        }

        // display board
        view.clearScreen();
        view.displayBoard();

        // close output.dat
        writer.close();
    }

    private BoardPosition convertToPosition(String moveAsDirection) {
        // Convert the cardinal direction given by the player to a move that can be used by the board.movePlayer method.
        // BoardPosition currentPosition = board.findPosition(currentPlayer);
        // switch (moveAsDirection) {
        //     case "N":
        // }
        // moveAsDirection
        // WIP
        BoardPosition placeholder = new BoardPosition(1, 1);
        return placeholder;

        // Having this method in both the TwoPlayerController and the ReplayController is redundant.
        // Would like to make a 'helper' methods class but unsure of best practices in Java for this.
    }
}
