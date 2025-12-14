package command;

/*
Charge l'état d'une perspective à partir d'un fichier JSON (voir HistoryManager.java).
Contrairement à save, on peut undo un load!
 */

import model.Perspective;
import model.PerspectiveMemento;
import persistence.PersistenceManager;

public class LoadPerspectiveCommand implements Command {

    private Perspective perspective;
    private String filename;
    private PerspectiveMemento before;

    public LoadPerspectiveCommand(Perspective p, String path) {
        this.perspective = p;
        this.filename = path;
    }

    @Override
    public void execute() {
        before = perspective.createMemento();
        try {
            PersistenceManager.load(perspective, filename);
            System.out.println("Chargement réussi : " + filename);
        } catch (Exception e) {
            System.err.println("Erreur de chargement : " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        if (before != null)
            perspective.restoreFromMemento(before);
    }
}
