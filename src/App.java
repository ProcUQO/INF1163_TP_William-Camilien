import model.*;
import view.*;
import controller.*;
/*
Un peu notre main!! Il va initialiser notre modèle, ses vues et contrôleurs.
 */
import javax.swing.*;

public class App {

    public static void main(String[] args) {

        // On initialise le modèele avec une image
        // Conseil de Cami : faire ctrl + clic gauche sur ImageModel pour se rediriger aux sections
        ImageModel model = new ImageModel("image.png");

        // On crée les trois perspectives
        Perspective p1 = new Perspective();
        Perspective p2 = new Perspective();
        Perspective p3 = new Perspective();
        model.addPerspective(p1);
        model.addPerspective(p2);
        model.addPerspective(p3);

        // On crée la fenêtre principale (pour William : tu peux gosser avec si tu veux le perfectionner!! J'ai pris des valeurs pas rapport)
        JFrame frame = new JFrame("MVC Image Viewer");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On crée des vues
        MainView main = new MainView(model, p1);
        ThumbnailView thumb = new ThumbnailView(model, p2);
        SecondaryView sec = new SecondaryView(model, p3);

        // On les ajoute à la fenêtre (je commence avec une seule vue, William tu peux Williamer là-dessus si tu veux)
        frame.add(main);
        frame.setVisible(true);

        // Le contrôleur qui gère la souris pour le zoom
        frame.addMouseWheelListener(new MouseController(model, p1));
    }
}
