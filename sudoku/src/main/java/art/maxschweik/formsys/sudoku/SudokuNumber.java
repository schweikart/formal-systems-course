package art.maxschweik.formsys.sudoku;

public enum SudokuNumber {
  ONE((byte) 1),
  TWO((byte) 2),
  THREE((byte) 3),
  FOUR((byte) 4),
  FIVE((byte) 5),
  SIX((byte) 6),
  SEVEN((byte) 7),
  EIGHT((byte) 8),
  NINE((byte) 9);

  private final byte value;

  SudokuNumber(byte value) {
    this.value = value;
  }

  public byte getValue() {
    return this.value;
  }

  public int getIndex() {
    return this.value - 1;
  }
}
