package view;
/*
C'est la vue miniature ou thumbnail.
 */
import model.ImageModel;
import model.Perspective;

public class ThumbnailView extends AbstractView {

    private Perspective perspective;

    public ThumbnailView(ImageModel model, Perspective p) {
        super(model, p);
    }


    @Override
    public void update() {
        repaint();
    }
}
