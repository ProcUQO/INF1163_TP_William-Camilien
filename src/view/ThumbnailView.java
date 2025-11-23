package view;
/*
C'est la vue miniature ou thumbnail.
 */
import model.ImageModel;
import model.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ThumbnailView extends AbstractView {

    private Perspective perspective;

    public ThumbnailView(ImageModel model, Perspective p) {
        super(model, p);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = model.getImage();
        if (img != null){
            g.drawImage(img, 0, 0, this);
        }
    }

    @Override
    public void update() {
        repaint();
    }


}
