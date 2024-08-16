package fr.eulbobo.dojo.gameoflife.domain.generator;

import java.awt.*;

public interface CellData {
    Point getCoordinates();

    boolean isAlive();
}
