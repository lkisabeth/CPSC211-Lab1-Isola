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
                    case Available:
                        System.out.print(" - ");
                        break;
                    case Player1:
                        System.out.print(" 1 ");
                        break;
                    case Player2:
                        System.out.print(" 2 ");
                        break;
                    case Missing:
                        System.out.print("   ");
                        break;
                }
            }
            System.out.println("");
        }
        System.out.println(" ****** BOARD ******");
        System.out.println("");

    }

    public void askForMove() {
        // Display prompt for user input
        System.out.println("Please enter your next move.");
        System.out.println("Moves should be represented by the cardinal direction in which you wish to move.");
        System.out.println("Choose from: [ N, NE, E, SE, S, SW, W, NW ]");
        System.out.print("Type your choice here: ");
    }

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
