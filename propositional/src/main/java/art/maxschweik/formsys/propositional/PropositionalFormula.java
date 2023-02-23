package art.maxschweik.formsys.propositional;

import java.util.Set;

public interface PropositionalFormula {
  boolean getValue(Interpretation interpretation);
  Set<Atom> getAtoms();
}
