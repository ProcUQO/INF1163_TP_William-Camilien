package unit.history;

import command.ZoomCommand;
import model.Perspective;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZoomCommandTest {

    @Test
    void execute_shouldIncreaseScale_whenZoomIn() {
        Perspective p = new Perspective();
        double before = p.getScale();

        new ZoomCommand(p, -1).execute(); // zoom in

        assertTrue(p.getScale() > before);
    }

    @Test
    void execute_shouldDecreaseScale_whenZoomOut() {
        Perspective p = new Perspective();
        double before = p.getScale();

        new ZoomCommand(p, 1).execute(); // zoom out

        assertTrue(p.getScale() < before);
    }

    @Test
    void undo_shouldRestoreScale() {
        Perspective p = new Perspective();
        ZoomCommand cmd = new ZoomCommand(p, -1);

        double before = p.getScale();
        cmd.execute();
        cmd.undo();

        assertEquals(before, p.getScale());
    }
}