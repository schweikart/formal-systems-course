package art.maxschweik.formsys.propositional;

import java.util.Set;

public class Atom implements PropositionalFormula {
  private final String name;

  public Atom(String name) {
    this.name = name;
  }

  @Override
  public boolean getValue(Interpretation interpretation) {
    return interpretation.getValue(this);
  }

  @Override
  public Set<Atom> getAtoms() {
    return Set.of(this);
  }

  @Override
  public String toString() {
    return this.name;
  }
}
