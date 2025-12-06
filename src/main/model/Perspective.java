package main.model;
/*
C'est une perspective d'affichage de l'image (donc l'image avec son zoom ou translation).
Utilise d'ailleurs Memento pour sauvegarder et restaurer son état.
 */
import main.view.Observer;
import java.util.ArrayList;
import java.util.List;

public class Perspective {

    private double scale = 1.0; // Facteur zoom
    private int translationX = 0;
    private int translationY = 0;
    private List<Observer> observers = new ArrayList<>();

    // oN AJOUTE UN observateur à la perspective
    public void addObserver(Observer obs) {
        observers.add(obs);
    }
    // Pour notifier chaque observateur
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
    // On crée le memento de l'état actuel
    public PerspectiveMemento createMemento() {
        return new PerspectiveMemento(scale, translationX, translationY);
    }

    // On restaure la perspective à partir d'un memento (m étant le memento qu'on reprend)
    public void restoreFromMemento(PerspectiveMemento m) {
        this.scale = m.getScale();
        this.translationX = m.getTranslationX();
        this.translationY = m.getTranslationY();
        notifyObservers();
    }

    // On définit le zoom (son facteur) avec son nouvea facteur.
    public void setScale(double scale) {
        this.scale = scale;
        notifyObservers();
    }

    // Même chose pour les translations
    public void setTranslation(int x, int y) {
        this.translationX = x;
        this.translationY = y;
        notifyObservers();
    }
    // Getters
    public double getScale() { return scale; }
    public int getTranslationX() { return translationX; }
    public int getTranslationY() { return translationY; }
}
