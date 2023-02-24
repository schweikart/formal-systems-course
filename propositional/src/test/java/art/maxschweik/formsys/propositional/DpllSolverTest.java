package art.maxschweik.formsys.propositional;

import static org.junit.jupiter.api.Assertions.assertFalse;

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

    assertFalse(new DpllSolver(cnf).solve());
  }
}
