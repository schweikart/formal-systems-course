package art.maxschweik.formsys.propositional;

import java.util.HashMap;
import java.util.Map;

public class InterpretationBuilder {
  private final Map<Atom, Boolean> values = new HashMap<>();

  public InterpretationBuilder with(Atom atom, boolean value) {
    this.values.put(atom, value);
    return this;
  }

  public Interpretation build() {
    return new Interpretation(this.values);
  }
}
