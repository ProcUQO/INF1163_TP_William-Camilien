package view;
/*
C'est la vue miniature ou thumbnail.
 */
import model.ImageModel;
import model.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ThumbnailView extends JPanel implements Observer {

    private final ImageModel model;
    private final Perspective mainPerspective;
    private final MainView mainview;

    public ThumbnailView(ImageModel model, Perspective p, MainView mainview) {
        this.model = model;
        this.mainPerspective = p;
        this.mainview = mainview;
        p.addObserver(this);
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.lightGray);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = model.getImage();
        if(img == null) return;

        Graphics g2 = (Graphics) g;
        int width = getWidth();
        int height = getHeight();
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        double scaleMiniImg = Math.min((double) width / imgWidth, (double) height / imgHeight);
        int scaleWidth = (int) ((imgWidth * scaleMiniImg)/2);
        int scaleHeight = (int) ((imgHeight * scaleMiniImg)/2);

        int offsetX = (width - scaleWidth) / 2;
        int offsetY = (height - scaleHeight) / 2;

        g2.drawImage(img, offsetX, offsetY, scaleWidth, scaleHeight, this);
        g2.setColor(Color.darkGray);
        g2.drawRect(0, 0, width, height);
    }



}
