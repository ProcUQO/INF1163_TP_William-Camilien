package main.view;
/*
C'est la vue principale de l'application qui affiche l'image.
 */
import main.model.ImageModel;
import main.model.Perspective;

public class MainView extends AbstractView {

    public MainView(ImageModel model, Perspective p) {
        super(model, p);
    }
}
