package art.maxschweik.formsys.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmicSudokuSolver {
  private final SudokuNumber[][] solution;
  private final SudokuBoard board;
  private List<SudokuNumber>[][] possibleNumbers;

  public AlgorithmicSudokuSolver(SudokuBoard board) {
    this.board = board;
    this.possibleNumbers = new List[9][9];

    List<SudokuNumber> allNumbersImmutable = Arrays.asList(SudokuNumber.values());
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        possibleNumbers[row][col] = new ArrayList<>();
        possibleNumbers[row][col].addAll(allNumbersImmutable);
      }
    }

    this.solution = new SudokuNumber[9][9];
  }

  public SudokuBoard solve() {
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        if (this.board.getNumberAt(row, col) != null) {
          this.setNumber(row, col, this.board.getNumberAt(row, col));
        }
      }
    }

    boolean repeat = true;
    while (repeat) {
      repeat = false;

      for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
          List<SudokuNumber> fieldPossibleNumbers = possibleNumbers[row][col];
          if (solution[row][col] == null && fieldPossibleNumbers.size() == 1) {
            setNumber(row, col, fieldPossibleNumbers.get(0));
            repeat = true;
          }
        }
      }
    }

    return new SudokuBoard(this.solution);
  }

  private void setNumber(int row, int col, SudokuNumber number) {
    if (solution[row][col] != null) {
      throw new IllegalStateException("field was already set!");
    }

    solution[row][col] = number;

    // cancel number in column
    for (int otherRow = 0; otherRow < 9; otherRow++) {
      possibleNumbers[otherRow][col].remove(number);
    }

    // cancel number in row
    for (int otherCol = 0; otherCol < 9; otherCol++) {
      possibleNumbers[row][otherCol].remove(number);
    }

    // cancel number in region
    int regionRow = row / 3;
    int regionCol = col / 3;
    for (int rowInRegion = 0; rowInRegion < 3; rowInRegion++) {
      int otherRow = regionRow * 3 + rowInRegion;

      for (int colInRegion = 0; colInRegion < 3; colInRegion++) {
        int otherCol = regionCol * 3 + colInRegion;

        possibleNumbers[otherRow][otherCol].remove(number);
      }
    }
  }
}
