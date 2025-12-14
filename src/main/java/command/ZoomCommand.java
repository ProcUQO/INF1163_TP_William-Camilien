package command;
/*
Patron Command. On encapsule une commande zoom in ou out sur la perspective.
Comme pour TranslateCommand, on stocke l'état pour si on veut undo.
 */
import model.Perspective;
import model.PerspectiveMemento;

public class ZoomCommand implements Command {

    private Perspective perspective; // La perspective qu'on modifie
    private PerspectiveMemento before; // Son état avant la modif pour le undo
    private int direction; // -1, c'est un zoom in, 1 c'est un zoom out.

    // p = perspective et zoomDirection = direction du zoom, qui est un -1 ou 1.
    public ZoomCommand(Perspective p, int zoomDirection) {
        this.perspective = p;
        this.direction = zoomDirection;
    }

    /*
    On fait le zoom (mais on sauvegarde premièrement l'état avant de l'appliquer)
     */
    @Override
    public void execute() {
        before = perspective.createMemento();
        double scale = perspective.getScale();
        scale += (direction < 0 ? 0.1 : -0.1);
        perspective.setScale(Math.max(0.1, scale));
    }

    // Pour annuler, on restaure l'ancien état.
    @Override
    public void undo() {
        perspective.restoreFromMemento(before);
    }

}
