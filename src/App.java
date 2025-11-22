
import model.*;
import view.*;
import controller.*;
/*
Un peu notre main!! Il va initialiser notre modèle, ses vues et contrôleurs.
On peut modifier la taille de la fenêtre, les boutons, etc..
 */
import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        // On initialise le modèele avec une image. Pas grave si elle ne charge pas, on peut en charger manuellement de toute façon.
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

        frame.add(main);

        // Le menu et ses boutons
        JPanel topPanel = new JPanel();

        JButton zoomInBtn = new JButton("+");
        JButton zoomOutBtn = new JButton("-");
        JButton saveBtn = new JButton("Sauvegarder");
        JButton loadBtn = new JButton("Charger");

        topPanel.add(zoomInBtn);
        topPanel.add(zoomOutBtn);
        topPanel.add(saveBtn);
        topPanel.add(loadBtn);

        frame.add(topPanel, BorderLayout.NORTH);
        MenuController menuCtrl = new MenuController(model, p1);

        JButton loadImageBtn = new JButton("Charger image");
        topPanel.add(loadImageBtn);
        loadImageBtn.addActionListener(e -> menuCtrl.loadImage());

        // Pour le undo redo, bien sûr
        JButton undoBtn = new JButton("Undo");
        JButton redoBtn = new JButton("Redo");
        topPanel.add(undoBtn);
        topPanel.add(redoBtn);

        // Comme en prog 2, on veut des listener pour savoir quand les boutons font de quoi :)
        zoomInBtn.addActionListener(e -> menuCtrl.zoomIn());
        zoomOutBtn.addActionListener(e -> menuCtrl.zoomOut());

        saveBtn.addActionListener(e -> menuCtrl.saveAction());
        loadBtn.addActionListener(e -> menuCtrl.loadAction());

        undoBtn.addActionListener(e -> menuCtrl.undo());
        redoBtn.addActionListener(e -> menuCtrl.redo());
        ThumbnailView thumb = new ThumbnailView(model, p2);
        SecondaryView sec = new SecondaryView(model, p3);

        // Le contrôleur qui gère la souris pour le zoom
        frame.addMouseWheelListener(new MouseController(model, p1));

        // Encore le contrôleur de la souris, mais cette fois pour le drag (merci william)
        ImageDragController drag = new ImageDragController(model, p1);
        main.addMouseListener(drag);
        main.addMouseMotionListener(drag);


        // On les ajoute à la fenêtre (je commence avec une seule vue, William tu peux Williamer là-dessus si tu veux)
        frame.add(main);
        frame.setVisible(true);

    }
}
