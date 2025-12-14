package strategy;
    /*
    Pour définir le contrat entre les tratégies de copie entre deux perspectives (voir document)
    Pour indépendamment gérer la copie de léchelle, la translation, les deux ou rien.
     */
import model.Perspective;
// On copie les paramètres d'affichage d'une source vers une cible (perspective vers perspective, from la soruce to la cible)
public interface CopyStrategy {
    void copy(Perspective from, Perspective to);

}
