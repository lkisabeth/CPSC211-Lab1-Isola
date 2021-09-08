import java.io.IOException;

public class GameView {
    private IsolaBoard board;

    public GameView(IsolaBoard board) {
        this.board = board;
    }

    public void displayBoard() {
        // Display the board
        System.out.println("");
        System.out.println(" ****** ISOLA ******");
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                switch (board.get(i, j)) {
                    case Available -> System.out.print(" - ");
                    case Player1 -> System.out.print(" 1 ");
                    case Player2 -> System.out.print(" 2 ");
                    case Missing -> System.out.print("   ");
                }
            }
            System.out.println("");
        }
        System.out.println(" ****** BOARD ******");
        System.out.println("");

    }

    public void askForMove(BoardSpace currentPlayer) {
        // Display Current Player
        System.out.println("** " + currentPlayer.toString() + "'s turn! **");
        System.out.println("");

        // Display prompt for user input
        System.out.println("Please enter your next move.");
        System.out.println("Moves should be represented by the cardinal direction in which you wish to move.");
        System.out.println("Choose from: [ N, NE, E, SE, S, SW, W, NW ]");
        System.out.print("Type your choice here: ");
    }

    public void displayWinner(BoardSpace currentPlayer) {
        System.out.println("*** " + currentPlayer.toString() + " Wins! ***");
    }

    // this isn't working. intention is to clear the terminal so the view appears to update instead of making a long list of outputs in the terminal
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("There was an error when trying to clear the screen...");
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
