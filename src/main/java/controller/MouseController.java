package main.controller;
/*
Actions de la souris, comme la molette pour zoomer.
 */
import main.model.ImageModel;
import main.command.ZoomCommand;
import main.model.Perspective;

import java.awt.event.*;

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
