package controller;

import command.TranslateCommand;
import model.ImageModel;
import model.Perspective;

import java.awt.Point;
import java.awt.event.*;


public class ImageDragController extends Controller implements MouseMotionListener{

    private Perspective perspective;
    private Point currentPoint;
    private Point pastPos;

    public ImageDragController(ImageModel model, Perspective p){
        super(model);
        this.perspective = p;
    }

    public void mouseDragged(MouseEvent e){
        currentPoint = e.getPoint();
        this.pastPos = DragController.previousPoint;

        executeCommand(new TranslateCommand(perspective,
                (int)currentPoint.getX() - (int)pastPos.getX(),
                (int)currentPoint.getY() - (int)pastPos.getY()));

    }

    public void mouseMoved(MouseEvent e){

    }

}
