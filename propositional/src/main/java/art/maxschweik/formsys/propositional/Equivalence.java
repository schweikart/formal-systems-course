package art.maxschweik.formsys.propositional;

public class Equivalence extends BinaryOperator {
  private static final char EQUIVALENCE_SIGN = '=';

  public Equivalence(PropositionalFormula left, PropositionalFormula right) {
    super(left, right, EQUIVALENCE_SIGN);
  }

  @Override
  public boolean getValue(Interpretation interpretation) {
    return getLeft().getValue(interpretation) == getRight().getValue(interpretation);
  }
}
