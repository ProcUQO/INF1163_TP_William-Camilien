package controller;

import command.TranslateCommand;
import model.ImageModel;
import model.Perspective;

import java.awt.Point;
import java.awt.event.*;

/**********
 * Cette class nous permet d'obtenir les valeur pour créé la translation de notre image selon le
 * drag de la souris dans l'écran.
 ***********/
public class ImageDragController extends Controller implements MouseMotionListener{

    private Perspective perspective;
    private Point currentPoint;
    private Point pastPos;

    //constructeur de la class
    public ImageDragController(ImageModel model, Perspective p){
        super(model);
        this.perspective = p;
    }
    // On vérifie la position de la souris lors du maintien du click.
    public void mouseDragged(MouseEvent e){
        currentPoint = e.getPoint();
        this.pastPos = DragController.previousPoint;

        //permet d'exécuter la translation de l'image avec une appel de fonction
        executeCommand(new TranslateCommand(perspective,
                (int)currentPoint.getX()-(int)pastPos.getX(),
                (int)currentPoint.getY()-(int)pastPos.getY()));

    }

    public void mouseMoved(MouseEvent e){}

}
