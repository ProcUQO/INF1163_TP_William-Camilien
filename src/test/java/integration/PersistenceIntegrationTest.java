package test.java.integration;
import main.command.LoadPerspectiveCommand;
import main.command.SavePerspectiveCommand;
import main.model.Perspective;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

// Comme PersistenceTest mais avec des path absolu (peut être pas nécessaire j'avoue, mais ça ne fait pas mal d'essayer)
// basé du devis (Save -> Load restaure exactement l’état)

public class PersistenceIntegrationTest {
    @Test
    void saveThenLoadThroughCommandsRestoresPerspective() throws Exception {
        Perspective p = new Perspective();
        p.setScale(2.0);
        p.setTranslation(100, 50);

        File tmp = File.createTempFile("persp", ".json");

        new SavePerspectiveCommand(p, tmp.getAbsolutePath()).execute();

        Perspective restored = new Perspective();
        new LoadPerspectiveCommand(restored, tmp.getAbsolutePath()).execute();

        assertEquals(p.getScale(), restored.getScale());
        assertEquals(p.getTranslationX(), restored.getTranslationX());
        assertEquals(p.getTranslationY(), restored.getTranslationY());
    }

}
