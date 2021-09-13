import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

class FileHandlerTest {

    @Test
    void createNewSaveFile() {
        FileHandler fileHandler = new FileHandler();

        fileHandler.createNewSaveFile();

        if (! fileHandler.getGameFile().getPath().equals("game.dat"))
            fail("game.dat was not created.");
    }

    @Test
    void saveMove() {
    }

    @Test
    void closeWriter() {
    }

    @Test
    void openGameFile() {
    }

    @Test
    void loadNextMove() {
    }

    @Test
    void closeScanner() {
    }

    @Test
    void convertToPosition() {
    }
}