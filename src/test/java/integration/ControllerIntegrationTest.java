package test.java.integration;
import main.command.ZoomCommand;
import main.controller.MenuController;
import main.model.ImageModel;
import main.model.Perspective;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Pour vérifier si le menu et la souris font les même effets pour le modèle (bref contrôleur -> commande -> modèle).

class ControllerIntegrationTest {

    @Test
    void menuZoomAndDirectCommandProduceSameEffect() {
        Perspective p1 = new Perspective();
        ImageModel model = new ImageModel(null);

        MenuController menu = new MenuController(model, p1);

        menu.zoomIn(); // via contrôleur
        double scaleAfterMenu = p1.getScale();

        Perspective p2 = new Perspective();
        new ZoomCommand(p2, -1).execute(); // via commande directe

        assertEquals(p2.getScale(), scaleAfterMenu);
    }
}
