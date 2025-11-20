package controller;
/*
ESSENTIEL, représente les commandes par menu (et non par la souris)
 */
import command.LoadImageCommand;
import command.LoadPerspectiveCommand;
import command.SavePerspectiveCommand;
import command.ZoomCommand;
import model.ImageModel;
import model.Perspective;

import javax.swing.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter; // pour filtrer les résultats du file explorer de java qui est un peu dégueue sinon

public class MenuController extends Controller {

    private Perspective perspective;

    public MenuController(ImageModel model, Perspective p) {
        super(model);
        this.perspective = p;
    }

    // Bouton : Sauvegarder
    public void saveAction() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Enregistrer la perspective");
        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            new SavePerspectiveCommand(perspective, file.getAbsolutePath()).execute();
        }
    }

    // Bouton : Charger
    public void loadAction() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Charger une perspective");

        // J'ai ajouté ceci pour qu'on aille juste les fichiers json dans le file explorer de java, sinon c'était un peu nul
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers JSON", "json");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            new LoadPerspectiveCommand(perspective, path).execute();
        }
    }


    public void zoomIn() {
        // direction -1 dans ton code = zoom in
        new ZoomCommand(perspective, -1).execute();
    }

    public void zoomOut() {
        // direction 1 dans ton code = zoom out
        new ZoomCommand(perspective, 1).execute();
    }

    // C'est le bouton pour loader une image qu'on va charger depuis notre app (comme le reste en fait)
    public void loadImage() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choisir une image");

        // Beau filtre pour seulement avoir les images
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                        "Images (png, jpg, jpeg, gif)",
                        "png", "jpg", "jpeg", "gif"
                );
        chooser.setFileFilter(filter);

        // On cache les fichiers non filtrés :
        chooser.setAcceptAllFileFilterUsed(false);

        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath();
            new LoadImageCommand(model, path).execute();
        }
    }


}

    /* Zoom avant.
    public void zoomIn() {
        executeCommand(new ZoomCommand(perspective, -1));
    }

    // Arrière.
    public void zoomOut() {
        executeCommand(new ZoomCommand(perspective, 1));
    }
    */

