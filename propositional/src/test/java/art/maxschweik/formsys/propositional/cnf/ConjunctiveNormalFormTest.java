package art.maxschweik.formsys.propositional.cnf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import art.maxschweik.formsys.propositional.And;
import art.maxschweik.formsys.propositional.Atom;
import art.maxschweik.formsys.propositional.Not;
import art.maxschweik.formsys.propositional.Or;
import art.maxschweik.formsys.propositional.PropositionalFormula;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ConjunctiveNormalFormTest {
  @Test
  void testParsing() {
    Atom[] P = new Atom[]{
        new Atom("P1"),
        new Atom("P2"),
        new Atom("P3"),
        new Atom("P4")
    };

    PropositionalFormula[] clauseFormulae = new PropositionalFormula[]{
        new Or(P[0], new Or(P[1], P[2])),
        new Or(new Not(P[0]), new Or(P[1], new Not(P[3]))),
        new Or(new Not(P[0]), P[2]),
        new Or(new Not(P[0]), new Or(new Not(P[2]), P[3])),
        new Or(P[0], new Not(P[2])),
        new Not(P[1])
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

    ConjunctiveNormalForm expected = new ConjunctiveNormalForm(Set.of(
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
    ));

    assertEquals(expected, cnf);
  }
}
