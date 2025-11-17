package history;
/*
Pour gérer l'historique des commandes exécutées pour faire des undo et redo. C'est un Singleton pour une seule
gestion globale de l'historique (voir document)
 */
import command.Command;
import java.util.Stack;
// pas fini

public class HistoryManager {

    private static HistoryManager instance; // Notre belle instance unique de Singleton
    private Stack<Command> history = new Stack<>(); //Un pile de commandes exécutées, un peu expérimentale à travailler
    // Constructeur privé, on veut pas instancier directement
    private HistoryManager() {}
    // Retourne l'instance unique du singleton HistoryManager, et donc de HistoryManager.
    public static HistoryManager getInstance() {
        if (instance == null) instance = new HistoryManager();
        return instance;
    }
    // On ajoute une commande (cmd)
    public void push(Command cmd) {
        history.push(cmd);
    }
    // On annule la commmande du sommet de la pile et on fait undo.
    public void undo() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }

    // Il faut ajouter redo
}
