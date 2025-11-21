package controller;

import java.awt.event.*;

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



        executeCommand(new TranslateCommand(perspective, e.getX(), e.getY()));
    }

    public void mouseMoved(MouseEvent e){

    }
}
