package art.maxschweik.formsys.propositional.cnf;

import art.maxschweik.formsys.propositional.Atom;
import art.maxschweik.formsys.propositional.Not;
import art.maxschweik.formsys.propositional.PropositionalFormula;
import java.util.Objects;

public class Literal {
  private final Atom atom;
  private final boolean isPositive;

  public Literal(PropositionalFormula literalFormula) {
    PropositionalFormula atomFormula;
    if (literalFormula instanceof Not not) {
      this.isPositive = false;
      atomFormula = not.getContent();
    } else {
      this.isPositive = true;
      atomFormula = literalFormula;
    }

    if (atomFormula instanceof Atom atom) {
      this.atom = atom;
    } else {
      throw new IllegalArgumentException(String.format("Expected atom, got %s", atomFormula));
    }
  }

  public Literal(Atom atom, boolean isPositive) {
    this.atom = Objects.requireNonNull(atom);
    this.isPositive = isPositive;
  }

  public Literal opposite() {
    return new Literal(this.atom, !this.isPositive);
  }

  @Override
  public String toString() {
    if (isPositive) {
      return atom.toString();
    } else {
      return new Not(atom).toString();
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.atom, this.isPositive);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Literal other &&
        this.atom.equals(other.atom) &&
        this.isPositive == other.isPositive;
  }
}
