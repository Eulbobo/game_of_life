package fr.eulbobo.dojo.gameoflife.domain.display;

public interface CellDisplay {
    void alive(int x, int y);

    void dead(int x, int y);
}
