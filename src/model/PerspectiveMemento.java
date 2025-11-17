package model;
/*
C'est un état sauvegardé de la perspective.
Il ne peut pas être modifié une fois créé (c'est immuable, c'est une bonne chose!!!)
 */
public class PerspectiveMemento {

    // Valeurs sauvegardées
    private final double savedScale;
    private final int savedX;
    private final int savedY;

    // Constructeur qui construit le constructeur
    public PerspectiveMemento(double scale, int x, int y) {
        this.savedScale = scale;
        this.savedX = x;
        this.savedY = y;
    }

    public double getScale() { return savedScale; }
    public int getTranslationX() { return savedX; }
    public int getTranslationY() { return savedY; }
}
