package command;
/*
Encore le Patron Command. On encapsule une commande de déplacement, la translation pour la perspective.
Dans le fond, on stocke un état avant de le modifier pour pouvoir faire undo plus tard.
 */
import model.Perspective;
import model.PerspectiveMemento;

public class TranslateCommand implements Command {

    private Perspective perspective; // C'est la perspective qu'on modifie
    private PerspectiveMemento before; // Son état avant la modification pour le undo
    private int dx, dy; // Les déplacements x/y


    // p pour perspective à modifier, dx pour déplacement x et y pour, bah, déplacement y.
    public TranslateCommand(Perspective p, int dx, int dy) {
        this.perspective = p;
        this.dx = dx;
        this.dy = dy;
    }

    // On sauvegarde l'état actuel et ENSUITE on applique le déplacement.
    @Override
    public void execute() {
        before = perspective.createMemento();
        perspective.setTranslation(dx,dy);
    }

    // On reprend l'ancien état.
    @Override
    public void undo() {
        perspective.restoreFromMemento(before);
    }
}
