package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CellEnvironment {

    private final Cells cells;

    private CellEnvironment(Cells cells) {
        this.cells = cells;
    }

    public static CellEnvironmentBuilder builder() {
        return new CellEnvironmentBuilder();
    }

    public void next() {
        cells.verifyCellsNeighbourHood();
        cells.executeInstructions();
    }

    public void displayTo(CellDisplay display) {
        cells.displayTo(display);
    }

    public static class CellEnvironmentBuilder {
        private final Map<Point, Cell> cells = new HashMap<>();

        public CellEnvironmentBuilder from(CellGenerator cellGenerator) {
            for (CellData cellData : cellGenerator.allCells()) {
                Point point = cellData.getCoordinates();
                Cell cell = new Cell(new Point(point.x, point.y), cellData.isAlive());
                cells.put(point, cell);
            }
            return this;
        }

        public CellEnvironment build() {
            return new CellEnvironment(new Cells(cells));
        }
    }
}
