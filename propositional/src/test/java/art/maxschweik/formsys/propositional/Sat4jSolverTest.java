package art.maxschweik.formsys.propositional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import art.maxschweik.formsys.propositional.cnf.ConjunctiveNormalForm;
import org.junit.jupiter.api.Test;

class Sat4jSolverTest {
  @Test
  void testSolvable() {
    Atom[] P = new Atom[]{
        new Atom("P[0]"), // expected true
        new Atom("P[1]"), // expected false
        new Atom("P[2]"), // expected true
        new Atom("P[3]")  // expected true
    };

    PropositionalFormula[] clauseFormulae = new PropositionalFormula[]{
        new Or(P[0], new Or(P[1], P[2])), // satisfied by P[0]
        new Or(new Not(P[0]), new Or(P[1], P[3])), // satisfied by P[3]
        new Or(new Not(P[0]), P[2]), // satisfied by P[2]
        new Or(new Not(P[0]), new Or(new Not(P[2]), P[3])), // satisfied by P[3]
        new Or(P[0], new Not(P[2])), // satisfied by P[0]
        new Not(P[1]) // satisfied by P[1]
    };

    PropositionalFormula cnfFormula = new And(
        new And(
            new And(
                clauseFormulae[0],
                clauseFormulae[1]
            ),
            new And(
                clauseFormulae[2],
                clauseFormulae[3]
            )
        ),
        new And(
            clauseFormulae[4],
            clauseFormulae[5]
        )
    );

    var cnf = new ConjunctiveNormalForm(cnfFormula);

    Interpretation solution = new Sat4jSolver(cnf).solve();
    assertNotNull(solution);
    assertTrue(cnfFormula.getValue(solution));
  }
}
