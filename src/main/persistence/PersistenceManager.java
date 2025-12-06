package main.persistence;
/*
Gèere la sauvegarde et le chargement des perspectives avec le format JSON (voir document
On ne veut pasp erdre l'état d'une perspective.
 */
import main.model.Perspective;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersistenceManager {
    /*
    On sauvegarde l'état de la perspective (p pour perspective, filename pour le nom, IOException en cas d'erreur)
     */
    public static void save(Perspective p, String filename) throws IOException {
        // Vu que c'est un peu gossant d'écrire .json quand on veut un .json, cette ligne va rendre le truc automatique si c'est pas déjà fait
        if (!filename.toLowerCase().endsWith(".json")) {
            filename += ".json";
        }

        // Version JSON de la perspective qu'on crée et sauvegarde
        String json = "{\n" +
                "  \"scale\": " + p.getScale() + ",\n" +
                "  \"x\": " + p.getTranslationX() + ",\n" +
                "  \"y\": " + p.getTranslationY() + "\n" +
                "}";
        Files.write(Paths.get(filename), json.getBytes());
    }
    /*
    On charge l'état du fichier JSON.
     */
    public static void load(Perspective p, String filename) throws IOException {
        // On lit le contenu et on extrait les valeurs
        String content = new String(Files.readAllBytes(Paths.get(filename)));

        double scale = extractDouble(content, "scale");
        int x = (int) extractDouble(content, "x");
        int y = (int) extractDouble(content, "y");

        p.setScale(scale);
        p.setTranslation(x, y);
    }
    // Ce que j'appelle dans le load. C'est pour extraire les valeurs numériques du fichier. Expérimental.
    private static double extractDouble(String json, String key) {
        int index = json.indexOf(key);
        if (index == -1) return 0;

        int colon = json.indexOf(":", index);
        int comma = json.indexOf(",", colon);
        if (comma == -1) comma = json.indexOf("}", colon); // On regarde la fin del 'bojet JSON, si y'a pas de virgule.
        // Parce qu'il faut un double
        return Double.parseDouble(json.substring(colon + 1, comma).trim());
    }
}
