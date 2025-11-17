package strategy;

import model.Perspective;
// On copie de la source vers la cible (voir Copy Strategy)
public class CopyTranslation implements CopyStrategy {
    @Override
    public void copy(Perspective from, Perspective to) {
        to.setTranslation(from.getTranslationX(), from.getTranslationY());
    }
    // pas fini
}
