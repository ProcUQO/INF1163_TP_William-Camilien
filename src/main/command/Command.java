package main.command;
/* On encapsule une action comme un objet pour simplifier la gestion des opérations
comme l'exécution, l'annulation, l'historique. C'est le patron Command.
 */
public interface Command {
    void execute(); // Exécute la commande
    void undo(); // Annule l'effet de la commande (voir document)
}
