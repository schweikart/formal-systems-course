package art.maxschweik.formsys.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PropositionalSudokuSolverTest {
  @Test
  void testWithLectureExample() {
    var board = LectureExample.getLectureSudokuBoard();
    var solution = LectureExample.getLectureSudokuSolution();

    assertEquals(solution, new PropositionalSudokuSolver(board).solve());
  }
}
