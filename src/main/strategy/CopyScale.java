package main.strategy;

import main.model.Perspective;
// On copie de la source vers la cible (voir Copy Strategy)
public class CopyScale implements CopyStrategy {
    @Override
    public void copy(Perspective from, Perspective to) {
        to.setScale(from.getScale());
    }
    // pas fini

}
