public class FileHandler {
    public void saveGame() {

    }

    public String loadGame() {

    }

    // refactor to load move entirely

    public BoardPosition convertToPosition(String cardinalDirection, IsolaBoard board, BoardSpace currentPlayer) {
        // Convert the cardinal direction given by the player to a move that can be used by the board.movePlayer method.
        BoardPosition currentPosition = board.findPosition(currentPlayer);
        int x = 0;
        int y = 0;
        switch (cardinalDirection) {
            case "N" -> y = -1;
            case "NE" -> {
                y = -1;
                x = 1;
            }
            case "E" -> x = 1;
            case "SE" -> {
                y = 1;
                x = 1;
            }
            case "S" -> y = 1;
            case "SW" -> {
                y = 1;
                x = -1;
            }
            case "W" -> x = -1;
            case "NW" -> {
                y = -1;
                x = -1;
            }
        }
        return new BoardPosition(currentPosition.getRow()+y, currentPosition.getColumn()+x);
    }
}
