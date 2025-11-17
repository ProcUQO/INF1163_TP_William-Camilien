package view;
/*
C'est la vue principale de l'application qui affiche l'image.
 */
import model.ImageModel;
import model.Perspective;

public class MainView extends AbstractView {

    public MainView(ImageModel model, Perspective p) {
        super(model, p);
    }
}
