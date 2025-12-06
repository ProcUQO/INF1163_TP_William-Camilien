package main.controller;

import java.awt.event.*;
import java.awt.Point;

import main.model.ImageModel;
import main.model.Perspective;

/**********
 * Cette class nous permet d'obtenir les valeur de la position avec le release du click
 *
 ***********/

public class DragController extends Controller implements MouseListener {

    private Perspective perspective;
    public static Point previousPoint = new Point(0,0);
    private Point pastPoint;
    // Constructeur de la class
    public DragController(ImageModel model, Perspective p){
        super(model);
        this.perspective = p;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    //Permet au release du click de la souris d'initier la position de la souris initial
    public void mouseReleased(MouseEvent e) {

        this.pastPoint = new Point(perspective.getTranslationX(), perspective.getTranslationY());
    }

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
}
