package fr.eulbobo.dojo.gameoflife.secondary;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StringCellDisplay implements CellDisplay {

    private final Map<Point, Boolean> points = new HashMap<>();

    private static int pointComparator(Map.Entry<Point, Boolean> pointCellEntry1, Map.Entry<Point, Boolean> pointCellEntry2) {
        if (pointCellEntry1.getKey().y == pointCellEntry2.getKey().y)
            return Integer.compare(pointCellEntry1.getKey().x, pointCellEntry2.getKey().x);
        return Integer.compare(pointCellEntry1.getKey().y, pointCellEntry2.getKey().y);
    }

    public void printTo(Consumer<String> printer) {
        StringBuilder sb = new StringBuilder();
        points.entrySet().stream().sorted(StringCellDisplay::pointComparator).forEach((entry) -> {
            if (entry.getKey().x == 0 && !sb.isEmpty()) {
                sb.append(System.lineSeparator());
            }
            sb.append(entry.getValue() ? "O" : ".");

        });
        sb.append(System.lineSeparator());
        printer.accept(sb.toString());
    }

    @Override
    public void alive(int x, int y) {
        points.put(new Point(x, y), Boolean.TRUE);
    }

    @Override
    public void dead(int x, int y) {
        points.put(new Point(x, y), Boolean.FALSE);
    }
}
