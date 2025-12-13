package test.java.integration;

import main.model.Perspective;
import main.strategy.CopyScale;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CopyIntegrationTest {

    @Test
    void copyScaleFromViewAToViewB() {
        Perspective source = new Perspective();
        source.setScale(3.0);

        Perspective target = new Perspective();

        new CopyScale().copy(source, target);

        assertEquals(3.0, target.getScale());
    }

}
