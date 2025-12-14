package controller;

import command.TranslateCommand;
import model.ImageModel;
import model.Perspective;
import model.PerspectiveMemento;
import java.awt.Point;
import java.awt.event.*;

/**********
 * Cette class nous permet d'obtenir les valeur pour créé la translation de notre image selon le
 * drag de la souris dans l'écran.
 ***********/
public class ImageDragController extends Controller implements MouseListener, MouseMotionListener {
    private Perspective perspective;
    private Point initialMousePoint;  // position initiale de la souris
    private PerspectiveMemento initialState;  // état initial de la perspective

    public ImageDragController(ImageModel model, Perspective p) {
        super(model);
        this.perspective = p;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialMousePoint = e.getPoint();
        initialState = perspective.createMemento();  // Sauvegarde l'état initial pour qu'on fasse undo redo plus tard :)
    }

    // Ce n'est pas le vrai mouvement qu'on applique, seulement temporaire, c'est pour éviter de faire un état de undo à chaque frame de déplacement
    @Override
    public void mouseDragged(MouseEvent e) {
        // Calcule la position temporaire (c'est fluide)
        int dx = e.getPoint().x - initialMousePoint.x;
        int dy = e.getPoint().y - initialMousePoint.y;
        // Met à jour la perspective (TEMPORAIRE, j'insiste, on enregistre pas)
        perspective.setTranslation(initialState.getTranslationX() + dx, initialState.getTranslationY() + dy);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // On fait le déplacement réel
        int totalDx = e.getPoint().x - initialMousePoint.x;
        int totalDy = e.getPoint().y - initialMousePoint.y;
        /* Restaure l'état initial (pour éviter le double déplacement)
         c'est une solution un peu weird mais, si tu regardes en mettant la ligne en
         commentaire, tu verras que ça empêche de faire un double saut (d'appliquer
         la distance de déplacement deux fois, avec le drag.)*/
        perspective.restoreFromMemento(initialState);
        // Applique le déplacement total une fois
        executeCommand(new TranslateCommand(perspective, totalDx, totalDy));
        // Réinitialise les champs
        initialState = null;
        initialMousePoint = null;
    }

    // Méthodes inutilisées
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
}
