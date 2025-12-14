package e2e;

import command.LoadPerspectiveCommand;
import command.SavePerspectiveCommand;
import model.Perspective;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fait avec l'aide de l'IA. Pour simuler un redémarrage, voir si la perspective est correcte après
public class RestartLoadTest {
    @Test
    void restartThenLoadRestoresState() throws Exception {
        Perspective p = new Perspective();
        p.setScale(1.8);
        p.setTranslation(40, 90);

        File tmp = File.createTempFile("persp", ".json");

        new SavePerspectiveCommand(p, tmp.getAbsolutePath()).execute();

        // "Redémarrage"
        Perspective afterRestart = new Perspective();

        new LoadPerspectiveCommand(afterRestart, tmp.getAbsolutePath()).execute();

        assertEquals(1.8, afterRestart.getScale());
        assertEquals(40, afterRestart.getTranslationX());
        assertEquals(90, afterRestart.getTranslationY());
    }

}
