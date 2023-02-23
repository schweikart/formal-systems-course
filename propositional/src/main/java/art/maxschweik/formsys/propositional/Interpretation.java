package art.maxschweik.formsys.propositional;

import java.util.HashMap;
import java.util.Map;

public class Interpretation {
  private final Map<Atom, Boolean> values;

  public Interpretation(Map<Atom, Boolean> values) {
    this.values = new HashMap<>(values);
  }

  public boolean getValue(Atom atom) {
    Boolean value = this.values.get(atom);
    if (value == null) {
      throw new IllegalArgumentException("atom is not part of this interpretation");
    }

    return value;
  }
}
