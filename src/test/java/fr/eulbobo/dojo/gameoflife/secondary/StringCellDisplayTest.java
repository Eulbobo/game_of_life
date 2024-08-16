package fr.eulbobo.dojo.gameoflife.secondary;

import fr.eulbobo.dojo.gameoflife.domain.CellEnvironment;
import fr.eulbobo.dojo.gameoflife.primary.StringCellGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StringCellDisplayTest {

    public static Stream<Arguments> cellData() {
        return Stream.of(
                Arguments.of("""
                        ...
                        .O.
                        ...
                        """, """
                        ...
                        ...
                        ...
                        """)
                ,
                Arguments.of("""
                        O..
                        .O.
                        ..O
                        """, """
                        ...
                        .O.
                        ...
                        """)
        );
    }

    @ParameterizedTest
    @MethodSource("cellData")
    public void should_display_cells_correctly(String input, String result) {
        StringCellDisplay stringCellDisplay = new StringCellDisplay();
        CellEnvironment environment = CellEnvironment.builder().from(new StringCellGenerator(input)).build();
        environment.next();
        environment.displayTo(stringCellDisplay);

        StringBuilder sb = new StringBuilder();
        stringCellDisplay.printTo(sb::append);

        Assertions.assertThat(sb.toString()).isEqualToIgnoringNewLines(result);
    }
}
