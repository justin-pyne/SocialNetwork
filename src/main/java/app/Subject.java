package app;

/**
 * Subject interface for the Subject-Observer design pattern.
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
