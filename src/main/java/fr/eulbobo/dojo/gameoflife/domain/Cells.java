package fr.eulbobo.dojo.gameoflife.domain;


import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

class Cells {

    private final Map<Point, Cell> cells;

    Cells(Map<Point, Cell> cells) {
        this.cells = cells;
    }

    public void verifyCellsNeighbourHood() {
        cells.values().forEach(c -> c.verifyCellsNeighbourHood(this));
    }

    void aliveNeighboursCountAround(Point point) {
        AtomicInteger found = new AtomicInteger(0);

        List.of(
                new Point(point.x - 1, point.y - 1),
                new Point(point.x, point.y - 1),
                new Point(point.x + 1, point.y - 1),

                new Point(point.x - 1, point.y + 1),
                new Point(point.x, point.y + 1),
                new Point(point.x + 1, point.y + 1),

                new Point(point.x - 1, point.y),
                new Point(point.x + 1, point.y)
        ).forEach(pt -> Optional.ofNullable(cells.get(pt)).filter(Cell.aliveCell()).ifPresent(c -> found.incrementAndGet()));

        cells.get(point).cellHasLiveNeighbours(found.get());
    }

    void displayTo(CellDisplay cellDisplay) {
        cells.values().forEach(c -> c.display(cellDisplay));
    }

    void executeInstructions() {
        cells.values().forEach(Cell::executeInstruction);
    }
}
