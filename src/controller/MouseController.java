package controller;
/*
Actions de la souris, comme la molette pour zoomer.
 */
import model.ImageModel;
import command.ZoomCommand;
import model.Perspective;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseController extends Controller implements MouseWheelListener {

    private Perspective perspective;

    // Le constructeur. Model est le modèle à contrôler, p la perspective contrôlée. Comme MenuController.
    public MouseController(ImageModel model, Perspective p) {
        super(model);
        this.perspective = p;
    }

    // Représente le mouvement de zoom avec la molette.
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        executeCommand(new ZoomCommand(perspective, e.getWheelRotation())); // getWheelRotation retourne un int, c'est plutôt cool!
    }
}
