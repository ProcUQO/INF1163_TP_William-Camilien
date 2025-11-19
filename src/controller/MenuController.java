package controller;
/*
ESSENTIEL, représente les commandes par menu (et non par la souris)
 */
import command.LoadPerspectiveCommand;
import command.SavePerspectiveCommand;
import command.ZoomCommand;
import model.ImageModel;
import model.Perspective;

import javax.swing.*;
import java.io.File;

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
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            new LoadPerspectiveCommand(perspective, file.getAbsolutePath()).execute();
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

