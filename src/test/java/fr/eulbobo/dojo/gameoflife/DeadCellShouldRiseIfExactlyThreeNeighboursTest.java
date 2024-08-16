package fr.eulbobo.dojo.gameoflife;

import fr.eulbobo.dojo.gameoflife.domain.CellEnvironment;
import fr.eulbobo.dojo.gameoflife.primary.StringCellGenerator;
import fr.eulbobo.dojo.gameoflife.secondary.StringCellDisplay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DeadCellShouldRiseIfExactlyThreeNeighboursTest {

    public static Stream<Arguments> shouldRise() {
        return Stream.of(
                Arguments.of("""
                        ...
                        OO.
                        .O.
                        """, """
                        ...
                        OO.
                        OO.
                        """)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldRise")
    public void dead_cell_should_rise_if_exactly_three_neighbours(String input, String result) {
        StringCellDisplay stringCellDisplay = new StringCellDisplay();
        CellEnvironment environment = CellEnvironment.builder().from(new StringCellGenerator(input)).build();
        environment.next();
        environment.displayTo(stringCellDisplay);

        StringBuilder sb = new StringBuilder();
        stringCellDisplay.printTo(sb::append);

        Assertions.assertThat(sb.toString()).isEqualToIgnoringNewLines(result);
    }
}
