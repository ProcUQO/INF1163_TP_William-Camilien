package history;

import command.Command;
import java.util.Stack;

/**
 * Gestionnaire global de l'historique (Singleton).
 * Permet d'annuler et rétablir les commandes exécutées.
 */
public class HistoryManager {

    private static HistoryManager instance;

    // Pile des commandes exécutées (peut être annulée)
    private Stack<Command> undoStack = new Stack<>();

    // Pile des commandes annulées (peut être refaites)
    private Stack<Command> redoStack = new Stack<>();

    private static final int MAX_SIZE = 30; // Limite arbitraire, on pourrait rendre la valeur publique plus tard

    private HistoryManager() {}

    public static HistoryManager getInstance() {
        if (instance == null) instance = new HistoryManager();
        return instance;
    }

    /**
     * Enregistrer une nouvelle commande dans l'historique.
     * - Vide le redo (nouvelle branche d’historique)
     * - Ajoute dans undo
     */
    public void push(Command cmd) {
        undoStack.push(cmd);
        redoStack.clear();

        // retrait de l'entrée la plus vieille
        if(undoStack.size() > MAX_SIZE) {
            undoStack.remove(0);
        }
    }

    /**
     * Annule la dernière commande.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
        }
    }

    /**
     * Ré-exécute la dernière commande annulée.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute(); // réapplique la commande
            undoStack.push(cmd);
        }
    }

    // On va seulement utiliser clear et size pour le debuggage, bref nos tests
    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    public int size() {
        return undoStack.size();
    }

    public int redoSize() {
        return redoStack.size();
    }
}
