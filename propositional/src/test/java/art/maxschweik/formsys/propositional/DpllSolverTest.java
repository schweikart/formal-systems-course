package art.maxschweik.formsys.propositional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import art.maxschweik.formsys.propositional.cnf.Clause;
import art.maxschweik.formsys.propositional.cnf.ConjunctiveNormalForm;
import art.maxschweik.formsys.propositional.cnf.Literal;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DpllSolverTest {
  @Test
  void testSolve() {
    Atom[] P = new Atom[]{
        new Atom("P1"),
        new Atom("P2"),
        new Atom("P3"),
        new Atom("P4")
    };
    Set<Clause> lectureExample = Set.of(
        new Clause(Set.of(
            new Literal(P[0], true),
            new Literal(P[1], true),
            new Literal(P[2], true)
        )),
        new Clause(Set.of(
            new Literal(P[0], false),
            new Literal(P[1], true),
            new Literal(P[3], false)
        )),
        new Clause(Set.of(
            new Literal(P[0], false),
            new Literal(P[2], true)
        )),
        new Clause(Set.of(
            new Literal(P[0], false),
            new Literal(P[2], false),
            new Literal(P[3], true)
        )),
        new Clause(Set.of(
            new Literal(P[0], true),
            new Literal(P[2], false)
        )),
        new Clause(Set.of(
            new Literal(P[1], false)
        ))
    );

    ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(lectureExample);

    Interpretation solution = new DpllSolver(cnf).solve();
    assertNull(solution);
  }

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

    Interpretation solution = new DpllSolver(cnf).solve();
    assertNotNull(solution);

    System.out.println(solution);

    assertTrue(cnfFormula.getValue(solution));
  }
}
