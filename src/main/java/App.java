package main;

import main.controller.ImageDragController;
import main.controller.MenuController;
import main.controller.MouseController;
import main.model.ImageModel;
import main.model.Perspective;
import main.view.MainView;
import main.view.SecondaryView;
import main.view.ThumbnailView;
import main.model.*;
import main.view.*;
import main.controller.*;
/*
Un peu notre main!! Il va initialiser notre modèle, ses vues et contrôleurs.
On peut modifier la taille de la fenêtre, les boutons, etc..
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

        // On initialise le modèele avec une image. Pas grave si elle ne charge pas, on peut en charger manuellement de toute façon.
        // Conseil de Cami : faire ctrl + clic gauche sur ImageModel pour se rediriger aux sections
        ImageModel model = new ImageModel("main/image.png");

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
        SecondaryView sec = new SecondaryView(model, p1, p3, main);
        ThumbnailView thumb = new ThumbnailView(model, p2, main);

        frame.add(main, BorderLayout.CENTER);

        // Chargement d'icones
        ImageIcon zoomInIcon = loadIcon("main/icones/zoom-in.png", 20);
        ImageIcon zoomOutIcon = loadIcon("main/icones/zoom-out.png", 20);
        ImageIcon undoIcon = loadIcon("main/icones/undo.png", 20);
        ImageIcon redoIcon = loadIcon("main/icones/redo.png", 20);

        // Le menu et ses boutons
        JPanel topPanel = new JPanel();

        JButton zoomInBtn = new JButton(zoomInIcon);
        JButton zoomOutBtn = new JButton(zoomOutIcon);
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
        JButton undoBtn = new JButton(undoIcon);
        JButton redoBtn = new JButton(redoIcon);
        topPanel.add(undoBtn);
        topPanel.add(redoBtn);

        // Vue secondaire
        JPanel rightPanel = new JPanel();
        JPanel sousPanel = new JPanel();
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setLayout(new GridLayout(2, 1));
        sousPanel.setLayout(new GridLayout(2,1));

        rightPanel.add(sec);
        rightPanel.add(sousPanel);
        sousPanel.add(emptyPanel);
        sousPanel.add(thumb, BorderLayout.SOUTH);

        frame.add(rightPanel, BorderLayout.EAST);


        // Comme en prog 2, on veut des listener pour savoir quand les boutons font de quoi :)
        zoomInBtn.addActionListener(e -> menuCtrl.zoomIn());
        zoomOutBtn.addActionListener(e -> menuCtrl.zoomOut());

        saveBtn.addActionListener(e -> menuCtrl.saveAction());
        loadBtn.addActionListener(e -> menuCtrl.loadAction());

        undoBtn.addActionListener(e -> menuCtrl.undo());
        redoBtn.addActionListener(e -> menuCtrl.redo());



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

    // Méthode pour charger une icône
    private static ImageIcon loadIcon(String path, int size) {
        try {
            Image img = ImageIO.read(new File(path));
            Image scaled = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (IOException e) {
            System.err.println("Could not load icon: " + path);
            return null;
        }
    }

}
