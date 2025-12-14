package integration;

import model.Perspective;
import strategy.CopyScale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
