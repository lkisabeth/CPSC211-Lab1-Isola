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

        // Create FileHandler
        FileHandler fileHandler = new FileHandler();

        // Create output.dat
        fileHandler.createNewSaveFile();

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
            fileHandler.saveMove(moveAsDirection);
            BoardPosition move = fileHandler.convertToPosition(moveAsDirection, board, currentPlayer);
            board.movePlayer(currentPlayer, move);
            if (currentPlayer == BoardSpace.Player1) {
                currentPlayer = BoardSpace.Player2;
            } else {
                currentPlayer = BoardSpace.Player1;
            }
        }

        // display board
        view.clearScreen();
        view.displayBoard();

        // close output.dat
        fileHandler.closeWriter();
    }
}
