package fr.eulbobo.dojo.gameoflife.primary;

import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringCellGenerator implements CellGenerator {

    List<CellData> cells = new ArrayList<>();
    private int x;
    private int y = -1;

    public StringCellGenerator(String input) {
        input.lines().forEach(l -> {
            x = 0;
            y++;
            for (int i = 0; i < l.length(); i++) {
                newCell(l.charAt(i) == 'O');
            }
        });
    }

    private void newCell(boolean alive) {
        cells.add(new InnerCellData(new Point(x++, y), alive));
    }

    @Override
    public Collection<CellData> allCells() {
        return cells;
    }

    private static class InnerCellData implements CellData {
        private final Point point;
        private final boolean alive;

        public InnerCellData(Point point, boolean alive) {
            this.point = point;
            this.alive = alive;
        }

        @Override
        public Point getCoordinates() {
            return point;
        }

        @Override
        public boolean isAlive() {
            return alive;
        }
    }
}
