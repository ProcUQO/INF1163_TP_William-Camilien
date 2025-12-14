package unit.model;

import model.Perspective;
import model.PerspectiveMemento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerspectiveTest {

    @Test
    void scale_shouldAlwaysBePositive() {
        Perspective p = new Perspective();
        p.setScale(2.0);
        assertTrue(p.getScale() > 0);

        p.setScale(0.5);
        assertTrue(p.getScale() > 0);

        // cas limite
        p.setScale(0.0001);
        assertTrue(p.getScale() > 0);
    }

    @Test
    void translation_shouldUpdateCorrectly() {
        Perspective p = new Perspective();
        p.setTranslation(10, -20);

        assertEquals(10, p.getTranslationX());
        assertEquals(-20, p.getTranslationY());
    }

    @Test
    void memento_shouldRestorePreviousState() {
        Perspective p = new Perspective();
        p.setScale(1.2);
        p.setTranslation(50, 80);

        PerspectiveMemento m = p.createMemento();

        // On change l'état
        p.setScale(3.0);
        p.setTranslation(-10, 0);

        // Restauration
        p.restoreFromMemento(m);

        assertEquals(1.2, p.getScale());
        assertEquals(50, p.getTranslationX());
        assertEquals(80, p.getTranslationY());
    }
}