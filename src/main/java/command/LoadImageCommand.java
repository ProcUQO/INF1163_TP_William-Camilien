package command;

import model.ImageModel;

public class LoadImageCommand implements Command {

    private ImageModel model;
    private String path;

    public LoadImageCommand(ImageModel model, String path) {
        this.model = model;
        this.path = path;
    }

    @Override
    public void execute() {
        model.loadImage(path);
    }

    @Override
    public void undo() {
        // On pourrait garder l’ancienne image mais non nécessaire pour ce TP
    }
}
