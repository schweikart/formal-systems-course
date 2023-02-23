package art.maxschweik.formsys.propositional;

import java.util.HashSet;
import java.util.Set;

public abstract class BinaryOperator implements PropositionalFormula {
  private final PropositionalFormula left;
  private final PropositionalFormula right;
  private final char sign;

  protected BinaryOperator(PropositionalFormula left, PropositionalFormula right, char sign) {
    this.left = left;
    this.right = right;
    this.sign = sign;
  }

  public PropositionalFormula getLeft() {
    return this.left;
  }

  public PropositionalFormula getRight() {
    return this.right;
  }

  @Override
  public Set<Atom> getAtoms() {
    var atoms = new HashSet<>(left.getAtoms());
    atoms.addAll(right.getAtoms());
    return atoms;
  }

  @Override
  public String toString() {
    return String.format("(%s %s %s)", left.toString(), sign, right.toString());
  }
}
