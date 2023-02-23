package art.maxschweik.formsys.propositional;

public class And extends BinaryOperator {
  private static final char AND_SIGN = '&'; // ∧

  public And(PropositionalFormula left, PropositionalFormula right) {
    super(left, right, AND_SIGN);
  }

  @Override
  public boolean getValue(Interpretation interpretation) {
    return getLeft().getValue(interpretation) && getRight().getValue(interpretation);
  }
}
