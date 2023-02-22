package art.maxschweik.formsys.sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlgorithmicSudokuSolverTest {
  @Test
  void testWithLectureExample() {
    SudokuBoard lectureExample = LectureExample.getLectureSudokuBoard();
    SudokuBoard lectureSolution = LectureExample.getLectureSudokuSolution();

    assertEquals(lectureSolution, new AlgorithmicSudokuSolver(lectureExample).solve());
  }
}
