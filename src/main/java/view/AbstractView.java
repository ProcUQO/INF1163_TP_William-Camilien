package view;
/*
Sert de base pour toutes les vues, implémente l'interface Observer pour les changements du modèl
 */
import model.ImageModel;
import model.Perspective;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractView extends JPanel implements Observer {

    protected ImageModel model; // Le modèele observé
    protected Perspective perspective;    // La perspective affiché

    //  model = modèle à observer, p = perspective à afficher
    public AbstractView(ImageModel model, Perspective p) {
        this.model = model;
        this.perspective = p;
        p.addObserver(this); // On s'abonne aux notifs de la perspective

        setBackground(Color.BLACK); // J'ai pris un background noir, mais ça peut être n'import quoi
    }
    // On met à jour la vue qui est appelée par le modèle
    @Override
    public void update() {
        repaint(); // Redessine la vue
    }

    // On dessine l'image selon notre perspective actuelle. g est le contexte graphique
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage img = model.getImage();
        if (img == null) return;

        Graphics2D g2 = (Graphics2D) g;

        double scale = perspective.getScale();
        int tx = perspective.getTranslationX();
        int ty = perspective.getTranslationY();

        // 1. Appliquer la translation
        g2.translate(tx, ty);

        // 2. Appliquer le zoom autour du coin supérieur gauche (oui, on peut changer ça plus tard)
        g2.scale(scale, scale);

        // 3. Dessiner l’image (voir la doc)
        g2.drawImage(img, 0, 0, this);
    }

}
