package main.controller;
/*
C'estn otre fameux abstract Controller (MVC et GRASP).
C'est donc la base pour nos contrôleurs, le blueprint qui va gérer l'exécution
d'une commande sur le modèle.
 */
import main.history.HistoryManager;
import main.model.ImageModel;
import main.command.Command;

public abstract class Controller {

    protected ImageModel model; // Le modèle à contrôler

    // Petit constructeur
    public Controller(ImageModel model) {
        this.model = model;
    }

    // On exécute la commande sur le modèle.
    protected void executeCommand(Command cmd) {
        cmd.execute();
        HistoryManager.getInstance().push(cmd);
    }
    
}
