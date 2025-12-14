package e2e;
// nom de classe le plus long au monde, basé du devis (undo agit seulement sur la vue active)
// Le changement de version java avait brisé une fonctionnalité, j'ai utilisé l'IA car je n'arrivais pas
// à trouver le problème.
import command.TranslateCommand;
import history.HistoryManager;
import model.Perspective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoAffectsOnlyActiveViewTest {
    @Test
    void undoOnlyAffectsActivePerspective() {
        Perspective p1 = new Perspective();
        Perspective p2 = new Perspective();

        HistoryManager history = HistoryManager.getInstance();
        history.clear();

        // 1. Créer et exécuter la première commande
        TranslateCommand cmd1 = new TranslateCommand(p1, 100, 0);
        cmd1.execute(); //
        history.push(cmd1);

        // 2. Créer et exécuter la deuxième commande
        TranslateCommand cmd2 = new TranslateCommand(p2, 50, 0);
        cmd2.execute();
        history.push(cmd2);

        // 3. Faire undo
        history.undo();

        // 4. Vérifier les résultats
        assertEquals(100, p1.getTranslationX());
        assertEquals(0, p2.getTranslationX());
    }

}
