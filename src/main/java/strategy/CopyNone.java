package main.strategy;

import main.model.Perspective;

public class CopyNone implements CopyStrategy {
    @Override
    public void copy(Perspective from, Perspective to) {
        // On ne fait absolument rien. I guess qu'on pourrait faire un son d'erreur!
    }
}
