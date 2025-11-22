package controller;
/*

 */
import command.TranslateCommand;
import model.ImageModel;
import model.Perspective;

import java.awt.Point;
import java.awt.event.*;

/**********
 * Cette class nous permet d'obtenir les valeur pour créé la translation de notre image selon le
 * drag de la souris dans l'écran.
 ***********/
public class ImageDragController extends Controller implements MouseListener, MouseMotionListener {

    private Perspective perspective;
    // C'est la dernière position de la souris connue pendant le dragging
    private Point previousPoint;
    // constructeur de la classe
    public ImageDragController(ImageModel model, Perspective p) {
        super(model);
        this.perspective = p;
    }

    // On l'appelle quand on clique pour commencer un drag (une fois)
    // Dans le fond, on garde en mémoire la position initiale de la souris (on va la déplacer à partir de ce point là)
    @Override
    public void mousePressed(MouseEvent e) {
        previousPoint = e.getPoint();
    }

    // Appelé pleiiiin de fois quand l'utilisateur tient la souris et la déplace
    // On va créer la commande TranslateCommand (notre pattern command) pour modifier la perspective
    @Override
    public void mouseDragged(MouseEvent e) {
        Point current = e.getPoint();

        // Déplacement entre deux frames (ou l'équivalent ordi je ne suis pas sûr, mais ça marche)
        int dx = current.x - previousPoint.x;
        int dy = current.y - previousPoint.y;

        executeCommand(new TranslateCommand(perspective, dx, dy));

        previousPoint = current;  // il faut mettre à jour la position d'avant
    }

    // Nécessaire pour l'interface, mais ça sera inutile :)
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
}
