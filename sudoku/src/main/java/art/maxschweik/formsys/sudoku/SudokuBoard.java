package art.maxschweik.formsys.sudoku;

import java.util.Arrays;
import java.util.Objects;

public class SudokuBoard {
  private static final int BOARD_WIDTH = 9;

  private final SudokuNumber[][] contents;

  public SudokuBoard() {
    this.contents = new SudokuNumber[BOARD_WIDTH][BOARD_WIDTH];
  }

  public SudokuBoard(SudokuNumber[][] contents) {
    this();

    Objects.requireNonNull(contents, "contents may not be null!");
    if (contents.length != BOARD_WIDTH) {
      throw new IllegalArgumentException(String.format("Invalid contents array length %d",
          contents.length));
    }

    for (int row = 0; row < this.contents.length; row++) {
      Objects.requireNonNull(contents[row], "contents rows may not be null!");
      if (contents[row].length != BOARD_WIDTH) {
        throw new IllegalArgumentException(String.format("Invalid contents row length %d",
            contents[row].length));
      }

      this.contents[row] = Arrays.copyOf(contents[row], BOARD_WIDTH);
    }
  }

  public SudokuNumber getNumberAt(int row, int col) {
    validateCoordinates(row, col);

    return this.contents[row][col];
  }

  private void validateCoordinates(int row, int col) {
    if (row < 0 || row >= 9) {
      throw new IllegalArgumentException(
          String.format("Invalid row '%d'! (expected 0 <= row < 9)", row));
    }

    if (col < 0 || col >= 9) {
      throw new IllegalArgumentException(
          String.format("Invalid col '%d'! (expected 0 <= col < 9)", col));
    }
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(this.contents);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof SudokuBoard other) {
      for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
          if (this.getNumberAt(row, col) != other.getNumberAt(row, col)) {
            return false;
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    StringBuilder visual = new StringBuilder();
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        visual.append(' ');
        SudokuNumber num = getNumberAt(row, col);
        visual.append(num == null ? " " : num.getValue());
        visual.append(' ');
        if (col == 2 || col == 5) {
          visual.append("|");
        }
      }
      visual.append("\n");

      if (row == 2 || row == 5) {
        visual.append("---------|---------|---------\n");
      }
    }
    return visual.toString();
  }
}
