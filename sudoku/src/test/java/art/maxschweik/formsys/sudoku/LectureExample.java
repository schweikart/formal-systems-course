package art.maxschweik.formsys.sudoku;

import static art.maxschweik.formsys.sudoku.SudokuNumber.EIGHT;
import static art.maxschweik.formsys.sudoku.SudokuNumber.FIVE;
import static art.maxschweik.formsys.sudoku.SudokuNumber.FOUR;
import static art.maxschweik.formsys.sudoku.SudokuNumber.NINE;
import static art.maxschweik.formsys.sudoku.SudokuNumber.ONE;
import static art.maxschweik.formsys.sudoku.SudokuNumber.SEVEN;
import static art.maxschweik.formsys.sudoku.SudokuNumber.SIX;
import static art.maxschweik.formsys.sudoku.SudokuNumber.THREE;
import static art.maxschweik.formsys.sudoku.SudokuNumber.TWO;

public class LectureExample {
  public static SudokuBoard getLectureSudokuBoard() {
    return new SudokuBoard(new SudokuNumber[][]{
        new SudokuNumber[]{FIVE, THREE, null, null, SEVEN, null,  null, null, null},
        new SudokuNumber[]{SIX, null, null,   ONE, NINE, FIVE,    null, null, null},
        new SudokuNumber[]{null, NINE, EIGHT, null, null, null,   null, SIX, null},

        new SudokuNumber[]{EIGHT, null, null, null, SIX, null,    null, null, THREE},
        new SudokuNumber[]{FOUR, null, null,  EIGHT, null, THREE, null, null, ONE},
        new SudokuNumber[]{SEVEN, null, null, null, TWO, null,    null, null, SIX},

        new SudokuNumber[]{null, SIX, null,   null, null, null,   TWO, EIGHT, null},
        new SudokuNumber[]{null, null, null,  FOUR, ONE, NINE,    null, null, FIVE},
        new SudokuNumber[]{null, null, null,  null, EIGHT, null,  null, SEVEN, NINE}
    });
  }

  public static SudokuBoard getLectureSudokuSolution() {
    return new SudokuBoard(new SudokuNumber[][]{
        new SudokuNumber[]{FIVE, THREE, FOUR, SIX, SEVEN,EIGHT,   NINE, ONE, TWO},
        new SudokuNumber[]{SIX, SEVEN, TWO,   ONE, NINE, FIVE,    THREE, FOUR, EIGHT},
        new SudokuNumber[]{ONE, NINE, EIGHT,  THREE, FOUR, TWO,   FIVE, SIX, SEVEN},

        new SudokuNumber[]{EIGHT, FIVE, NINE, SEVEN, SIX, ONE,    FOUR, TWO, THREE},
        new SudokuNumber[]{FOUR, TWO, SIX,    EIGHT, FIVE, THREE, SEVEN, NINE, ONE},
        new SudokuNumber[]{SEVEN, ONE, THREE, NINE, TWO, FOUR,    EIGHT, FIVE, SIX},

        new SudokuNumber[]{NINE, SIX, ONE,    FIVE, THREE, SEVEN, TWO, EIGHT, FOUR},
        new SudokuNumber[]{TWO, EIGHT, SEVEN, FOUR, ONE, NINE,    SIX, THREE, FIVE},
        new SudokuNumber[]{THREE, FOUR, FIVE, TWO, EIGHT, SIX,    ONE, SEVEN, NINE}
    });
  }
}
