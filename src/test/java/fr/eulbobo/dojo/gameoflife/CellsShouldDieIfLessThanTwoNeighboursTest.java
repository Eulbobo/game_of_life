package fr.eulbobo.dojo.gameoflife;

import fr.eulbobo.dojo.gameoflife.domain.CellEnvironment;
import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.primary.StringCellGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CellsShouldDieIfLessThanTwoNeighboursTest {

    public static Stream<Arguments> shouldDie() {
        return Stream.of(
                Arguments.of("""
                        ...
                        .O.
                        ...
                        """, List.of()),
                Arguments.of("""
                        ...
                        .OO
                        ...
                        """, List.of())
                ,
                Arguments.of("""
                        ...
                        OOO
                        ...
                        """, List.of(new Point(1, 1)))
        );
    }

    @ParameterizedTest
    @MethodSource("shouldDie")
    public void cell_should_die_if_less_that_two_neighbours(String input, List<Point> alivePoints) {
        CellDisplay cellDisplay = Mockito.mock(CellDisplay.class);
        CellEnvironment environment = CellEnvironment.builder().from(new StringCellGenerator(input)).build();
        environment.next();
        environment.displayTo(cellDisplay);

        if (alivePoints.isEmpty()) {
            verify(cellDisplay, times(9)).dead(any(Integer.class), any(Integer.class));
        } else {
            alivePoints.forEach(pt -> verify(cellDisplay).alive(eq(pt.x), eq(pt.y)));
        }
    }
}
