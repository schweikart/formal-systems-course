package art.maxschweik.formsys.propositional;

import java.util.Set;

public class Zero implements PropositionalFormula {
  @Override
  public boolean getValue(Interpretation interpretation) {
    return false;
  }

  @Override
  public Set<Atom> getAtoms() {
    return Set.of();
  }

  @Override
  public String toString() {
    return "0";
  }
}
