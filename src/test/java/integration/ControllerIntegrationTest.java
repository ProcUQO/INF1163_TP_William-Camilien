package integration;
import command.ZoomCommand;
import controller.MenuController;
import model.ImageModel;
import model.Perspective;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Pour vérifier si le menu et la souris font les même effets pour le modèle (bref contrôleur -> commande -> modèle).

class ControllerIntegrationTest {

    @Test
    void menuZoomAndDirectCommandProduceSameEffect() {
        Perspective p1 = new Perspective();
        ImageModel model = new ImageModel("src/main/java/image.png");

        MenuController menu = new MenuController(model, p1);

        menu.zoomIn(); // via contrôleur
        double scaleAfterMenu = p1.getScale();

        Perspective p2 = new Perspective();
        new ZoomCommand(p2, -1).execute(); // via commande directe

        assertEquals(p2.getScale(), scaleAfterMenu);
    }
}
