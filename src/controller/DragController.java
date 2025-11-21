package controller;

import java.awt.event.*;
import java.awt.MouseInfo;
import java.awt.Point;

import command.TranslateCommand;
import model.ImageModel;
import model.Perspective;

public class DragController extends Controller implements MouseMotionListener {

    private Perspective perspective;
    private boolean onImage = false;

    public DragController(ImageModel model, Perspective p){
        super(model);
        this.perspective = p;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        double mousePosX = mouseLocation.getX();
        double mousePosY = mouseLocation.getY();

        executeCommand(new TranslateCommand(perspective, (int)mousePosX, (int)mousePosY));
    }

    public void mouseMoved(MouseEvent e){

    }
}
