package art.maxschweik.formsys.propositional;

public class Implication extends BinaryOperator {
  private static final char IMPLICATION_SIGN = '>';

  public Implication(PropositionalFormula left, PropositionalFormula right) {
    super(left, right, IMPLICATION_SIGN);
  }

  @Override
  public boolean getValue(Interpretation interpretation) {
    return !getLeft().getValue(interpretation) || getRight().getValue(interpretation);
  }
}
