package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;

import java.awt.*;
import java.util.function.Predicate;

class Cell {

    private final Point point;
    private boolean alive;
    private boolean rise;
    private boolean die;

    Cell(Point point, boolean alive) {
        this.point = point;
        this.alive = alive;
    }

    public static Predicate<Cell> aliveCell() {
        return cell -> cell.alive;
    }

    void verifyCellsNeighbourHood(Cells cells) {
        cells.aliveNeighboursCountAround(point);
    }

    //1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
    //2. Any live cell with more than three live neighbours dies, as if by overcrowding.
    //3. Any live cell with two or three live neighbours lives on to the next generation.
    //4. Any dead cell with exactly three live neighbours becomes a live cell.
    void cellHasLiveNeighbours(int liveCellsAround) {
        if (alive && liveCellsAround < 2 || liveCellsAround > 3) {
            die = true;
        }
        if (!alive && liveCellsAround == 3) {
            rise = true;
        }
    }

    void executeInstruction() {
        if (rise) alive = true;
        if (die) alive = false;
        rise = false;
        die = false;
    }

    void display(CellDisplay cellDisplay) {
        if (alive) {
            cellDisplay.alive(point.x, point.y);
        } else {
            cellDisplay.dead(point.x, point.y);
        }
    }
}
