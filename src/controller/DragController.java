package controller;

import java.awt.event.*;
import java.awt.Point;

import model.ImageModel;
import model.Perspective;

public class DragController extends Controller implements MouseListener {

    private Perspective perspective;

    public static Point previousPoint;

    public DragController(ImageModel model, Perspective p){
        super(model);
        this.perspective = p;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(previousPoint == null){
            previousPoint = new Point(0,0);
        }
        perspective.setTranslation((int)previousPoint.getX(),(int)previousPoint.getY());
    }

    public void mouseClicked(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e) {
        previousPoint = e.getPoint();
    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }
}
