package main.model;
/*
Modèle de l'image, gère les perspecives associées. Notifie avec les observateurs s'il y a changement.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageModel {

    private String imagePath; // Chemin de l'image
    private BufferedImage image;     // Image loadée
    private List<Perspective> perspectives; // Une liste pour les perspectives associées.

    // TEMPORAIRE : Constructeur d'imageModel, prend le chemin de l'image à charger.
    public ImageModel(String path) {
        this.imagePath = path;
        this.perspectives = new ArrayList<>();

        // On veut seulement la charger une fois.
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Erreur : impossible de charger l'image : " + path);
        }
    }
    // Retourne l'image chargée
    public BufferedImage getImage() {
        return image;
    }
    // Ajouter une perspective (p) au modèle
    public void addPerspective(Perspective p) {
        perspectives.add(p);
        notifyObservers();
    }

    public void removePerspective(Perspective p) {
        perspectives.remove(p);
        notifyObservers();
    }

    public String getImagePath() {
        return imagePath;
    }

    public List<Perspective> getPerspectives() {
        return perspectives;
    }

    // Pour que tous les observateurs soient notifiés des perspectives.
    public void notifyObservers() {
        for (Perspective p : perspectives) {
            p.notifyObservers();
        }
    }

    // On l'appelle depuis LoadImageCommand, pour charger une image
    public void loadImage(String path) {
        this.imagePath = path;
        try {
            image = ImageIO.read(new File(path));
            notifyObservers();
        } catch (IOException e) {
            System.err.println("Erreur : impossible de charger l'image " + path);
        }
    }

}
