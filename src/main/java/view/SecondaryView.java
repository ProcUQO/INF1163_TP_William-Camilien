package main.view;

import main.model.ImageModel;
import main.model.Perspective;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
Similaire à mainview, mais avec un carré rouge qui représente la position de mainview.
 */
public class SecondaryView extends JPanel implements Observer {
    private final ImageModel model;
    private final Perspective mainPerspective;
    private final MainView mainView;

    public SecondaryView(ImageModel model, Perspective mainP, Perspective miniP, MainView mainView) {
        this.model = model;
        this.mainPerspective = mainP;
        this.mainView = mainView;
        mainP.addObserver(this);
        miniP.addObserver(this);
        setPreferredSize(new Dimension(220, 220));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = model.getImage();
        if (img == null) return;

        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        int imgW = img.getWidth();
        int imgH = img.getHeight();

        // 1) Scaler l'image en secondaire (plus petit que le main :) )
        double scaleMini = Math.min((double) w / imgW, (double) h / imgH);
        int scaledW = (int) (imgW * scaleMini);
        int scaledH = (int) (imgH * scaleMini);
        int offsetX = (w - scaledW) / 2;
        int offsetY = (h - scaledH) / 2;
        g2.drawImage(img, offsetX, offsetY, scaledW, scaledH, this);

        // le rectangle rouge (l'aspect principal du secondaryview)
        double mainScale = mainPerspective.getScale();
        int tx = mainPerspective.getTranslationX();
        int ty = mainPerspective.getTranslationY();

        // Taille de la MainView
        int mainViewWidth = mainView.getWidth();
        int mainViewHeight = mainView.getHeight();

        // Position du coin supérieur gauche de la zone visible dans l'image originale
        double visibleX = (-tx) / mainScale;
        double visibleY = (-ty) / mainScale;

        // Taille de la zone visible dans l'image originale
        double viewW = mainViewWidth / mainScale;
        double viewH = mainViewHeight / mainScale;

        // Ratios avec le réel (j'ai essayé parent.getWidth mais c'était pas réellement accurate)
        double ratioX = scaledW / (double) imgW;
        double ratioY = scaledH / (double) imgH;

        // convertit la position visible dans l’espace secondaire
        int rectX = offsetX + (int) (visibleX * ratioX);
        int rectY = offsetY + (int) (visibleY * ratioY);

        // convertit la taille visible dans l’espace secondaire
        int rectW = (int) (viewW * ratioX);
        int rectH = (int) (viewH * ratioY);

        // j'ai fais que le rectangle rouge n'est pas remplit, mais ça se modifie facilement.
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRect(rectX, rectY, rectW, rectH);
    }

}
