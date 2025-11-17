package controller;
/*
Représente les actions par menus (pas par la souris).
 */
import model.ImageModel;
import model.Perspective;
import command.ZoomCommand;

public class MenuController extends Controller {

    private Perspective perspective; // La perspective contrôlée.

    // Constructeur avec model (lui qu'on contrôle) et p (la perspective qu'on contrôle).
    public MenuController(ImageModel model, Perspective p) {
        super(model);
        this.perspective = p;
    }

    // Zoom avant.
    public void zoomIn() {
        executeCommand(new ZoomCommand(perspective, -1));
    }

    // Arrière.
    public void zoomOut() {
        executeCommand(new ZoomCommand(perspective, 1));
    }
}
