package test.java.e2e;
// nom de classe le plus long au monde, basé du devis (undo agit seulement sur la vue active)

import main.command.TranslateCommand;
import main.history.HistoryManager;
import main.model.Perspective;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class UndoAffectsOnlyActiveViewTest {
    @Test
    void undoOnlyAffectsActivePerspective() {
        Perspective p1 = new Perspective();
        Perspective p2 = new Perspective();

        HistoryManager history = HistoryManager.getInstance();
        history.clear();

        new TranslateCommand(p1, 100, 0).execute();
        history.push(new TranslateCommand(p1, 100, 0));

        new TranslateCommand(p2, 50, 0).execute();
        history.push(new TranslateCommand(p2, 50, 0));

        history.undo();

        assertEquals(100, p1.getTranslationX());
        assertEquals(0, p2.getTranslationX());
    }

}
