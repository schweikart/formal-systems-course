package art.maxschweik.formsys.propositional;

import java.util.Set;

public class Not implements PropositionalFormula {
  private static final char NOT_SIGN = '-'; // maybe also ¬
  private final PropositionalFormula content;

  public Not(PropositionalFormula content) {
    this.content = content;
  }

  public PropositionalFormula getContent() {
    return this.content;
  }

  @Override
  public boolean getValue(Interpretation interpretation) {
    return !content.getValue(interpretation);
  }

  @Override
  public Set<Atom> getAtoms() {
    return content.getAtoms();
  }

  @Override
  public String toString() {
    return String.format("%s%s", NOT_SIGN, this.content.toString());
  }
}
