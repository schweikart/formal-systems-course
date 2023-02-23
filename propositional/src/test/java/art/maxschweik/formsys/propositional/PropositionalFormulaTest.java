package art.maxschweik.formsys.propositional;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class PropositionalFormulaTest {
  @Test
  void testSimpleFormula() {
    Atom a1 = new Atom("a1");
    Atom a2 = new Atom("a2");
    Atom a3 = new Atom("a3");
    var formula = new Or(a1, new Equivalence(a2, a3));

    Interpretation i = new InterpretationBuilder()
        .with(a1, false)
        .with(a2, true)
        .with(a3, true)
        .build();

    assertSame(true, formula.getValue(i));
  }
}
