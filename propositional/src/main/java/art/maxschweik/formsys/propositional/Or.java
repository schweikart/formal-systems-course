package art.maxschweik.formsys.propositional;

public class Or extends BinaryOperator {
  private static final char OR_SIGN = '|'; // maybe also ∨ (unicode or) or v (letter)

  public Or(PropositionalFormula left, PropositionalFormula right) {
    super(left, right, OR_SIGN);
  }

  @Override
  public boolean getValue(Interpretation interpretation) {
    return getLeft().getValue(interpretation) || getRight().getValue(interpretation);
  }
}
