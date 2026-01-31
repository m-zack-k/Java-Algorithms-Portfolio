package cz.cuni.mff.miyazakk.toolkit;

public interface SimpleCollection<T> {

    void add(T o);
    T get(int i);
    void remove(T o);
    void remove(int i);

    int size();
}