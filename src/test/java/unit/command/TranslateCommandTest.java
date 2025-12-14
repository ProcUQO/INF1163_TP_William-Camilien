package unit.command;

import command.TranslateCommand;
import model.Perspective;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranslateCommandTest {

    @Test
    void execute_shouldUpdateTranslation() {
        Perspective p = new Perspective();
        int beforeX = p.getTranslationX();
        int beforeY = p.getTranslationY();

        new TranslateCommand(p, 20, -15).execute();

        assertEquals(beforeX + 20, p.getTranslationX());
        assertEquals(beforeY - 15, p.getTranslationY());
    }

    @Test
    void undo_shouldRestorePreviousTranslation() {
        Perspective p = new Perspective();
        TranslateCommand cmd = new TranslateCommand(p, 20, 10);

        int beforeX = p.getTranslationX();
        int beforeY = p.getTranslationY();

        cmd.execute();
        cmd.undo();

        assertEquals(beforeX, p.getTranslationX());
        assertEquals(beforeY, p.getTranslationY());
    }
}