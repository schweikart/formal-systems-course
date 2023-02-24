package art.maxschweik.formsys.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PropositionalSudokuSolverTest {
  @Test
  void testWithLectureExample() {
    var board = LectureExample.getLectureSudokuBoard();
    var expected = LectureExample.getLectureSudokuSolution();

    var actual = new PropositionalSudokuSolver(board).solve();
    assertNotNull(actual);
    assertEquals(expected, actual);
  }
}
