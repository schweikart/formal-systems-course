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
      return false;//throw new IllegalArgumentException("atom is not part of this interpretation");
    }

    return value;
  }

  public Interpretation withValue(Atom atom, boolean value) {
    var newMap = new HashMap<>(this.values);
    newMap.put(atom, value);
    return new Interpretation(newMap);
  }

  @Override
  public String toString() {
    StringBuilder representation = new StringBuilder();
    representation.append("Interpretation(");
    for (var entry : this.values.entrySet()) {
      representation.append(entry.getKey());
      representation.append('=');
      representation.append(entry.getValue());
      representation.append(", ");
    }
    representation.append(')');
    return representation.toString();
  }
}
