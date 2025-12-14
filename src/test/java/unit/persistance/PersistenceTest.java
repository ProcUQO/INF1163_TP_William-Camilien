package unit.persistance;

import command.SavePerspectiveCommand;
import command.LoadPerspectiveCommand;
import model.Perspective;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {

    @Test
    void saveThenLoad_shouldRestoreExactSamePerspective() throws Exception {
        Perspective p = new Perspective();
        p.setScale(2.5);
        p.setTranslation(120, -80);

        File temp = File.createTempFile("persp", ".json");

        new SavePerspectiveCommand(p, temp.getAbsolutePath()).execute();

        Perspective restored = new Perspective();
        new LoadPerspectiveCommand(restored, temp.getAbsolutePath()).execute();

        assertEquals(p.getScale(), restored.getScale());
        assertEquals(p.getTranslationX(), restored.getTranslationX());
        assertEquals(p.getTranslationY(), restored.getTranslationY());
    }

    @Test
    void load_shouldNotThrowWhenFileValid() throws Exception {
        Perspective p = new Perspective();
        File temp = File.createTempFile("persp", ".json");

        new SavePerspectiveCommand(p, temp.getAbsolutePath()).execute();
        Perspective restored = new Perspective();

        assertDoesNotThrow(() ->
                new LoadPerspectiveCommand(restored, temp.getAbsolutePath()).execute()
        );
    }
}