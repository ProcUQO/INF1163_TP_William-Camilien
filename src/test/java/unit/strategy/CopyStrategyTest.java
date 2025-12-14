package unit.strategy;

import model.Perspective;
import org.junit.jupiter.api.Test;
import strategy.CopyBoth;
import strategy.CopyNone;
import strategy.CopyScale;
import strategy.CopyTranslation;

import static org.junit.jupiter.api.Assertions.*;

public class CopyStrategyTest {

    @Test
    void copyScale_shouldCopyOnlyScale() {
        Perspective src = new Perspective();
        src.setScale(3.0);

        Perspective dst = new Perspective();
        dst.setScale(1.0);
        dst.setTranslation(500, 500);

        new CopyScale().copy(src, dst);

        assertEquals(3.0, dst.getScale());
        assertEquals(500, dst.getTranslationX());
        assertEquals(500, dst.getTranslationY());
    }

    @Test
    void copyTranslation_shouldCopyOnlyTranslation() {
        Perspective src = new Perspective();
        src.setTranslation(40, -40);

        Perspective dst = new Perspective();
        dst.setScale(4.0);
        dst.setTranslation(0, 0);

        new CopyTranslation().copy(src, dst);

        assertEquals(4.0, dst.getScale());
        assertEquals(40, dst.getTranslationX());
        assertEquals(-40, dst.getTranslationY());
    }

    @Test
    void copyBoth_shouldCopyEverything() {
        Perspective src = new Perspective();
        src.setScale(1.8);
        src.setTranslation(10, 10);

        Perspective dst = new Perspective();
        new CopyBoth().copy(src, dst);

        assertEquals(1.8, dst.getScale());
        assertEquals(10, dst.getTranslationX());
        assertEquals(10, dst.getTranslationY());
    }

    @Test
    void copyNone_shouldCopyNothing() {
        Perspective src = new Perspective();
        src.setScale(9);
        src.setTranslation(9, 9);

        Perspective dst = new Perspective();
        double originalScale = dst.getScale();
        int originalX = dst.getTranslationX();
        int originalY = dst.getTranslationY();

        new CopyNone().copy(src, dst);

        assertEquals(originalScale, dst.getScale());
        assertEquals(originalX, dst.getTranslationX());
        assertEquals(originalY, dst.getTranslationY());
    }
}