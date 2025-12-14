package command;

/*
Commande qui sauvegarde l'état d'une perspective dans un fichier JSON.
Cette commande N'AFFECTE PAS le modèle → elle n'entre pas dans l'historique Undo/Redo.
 */

import model.Perspective;
import persistence.PersistenceManager;

public class SavePerspectiveCommand implements Command {

    private Perspective perspective;
    private String filename;

    public SavePerspectiveCommand(Perspective p, String path) {
        this.perspective = p;
        this.filename = path;
    }

    @Override
    public void execute() {
        try {
            PersistenceManager.save(perspective, filename);
            System.out.println("Sauvegarde réussie : " + filename);
        } catch (Exception e) {
            System.err.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        // J'ai explicitement mis le undo même s'il ne sert à rien, vu qu'on ne va pas undo une sauvegarde :)
    }
}
