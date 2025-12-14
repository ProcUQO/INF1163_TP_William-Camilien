package strategy;
// Copie pour l'échelle ET la translation
import model.Perspective;

// On copie de la source vers la cible (voir Copy Strategy)
public class CopyBoth implements CopyStrategy {
    @Override
    public void copy(Perspective from, Perspective to) {
        to.setScale(from.getScale());
        to.setTranslation(from.getTranslationX(), from.getTranslationY());
    }
    // pas fini

}
