package art.maxschweik.formsys.propositional;

import java.util.Set;

public class One implements PropositionalFormula {
  @Override
  public boolean getValue(Interpretation interpretation) {
    return true;
  }

  @Override
  public Set<Atom> getAtoms() {
    return Set.of();
  }

  @Override
  public String toString() {
    return "1";
  }
}
