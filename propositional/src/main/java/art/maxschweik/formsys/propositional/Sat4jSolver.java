package art.maxschweik.formsys.propositional;

import art.maxschweik.formsys.propositional.cnf.ConjunctiveNormalForm;
import art.maxschweik.formsys.propositional.cnf.Literal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

public class Sat4jSolver {
  private final ConjunctiveNormalForm cnf;
  private final Map<Atom, Integer> atomIds = new HashMap<>();
  private final Map<Integer, Atom> idAtoms = new HashMap<>();
  private int nextId = 1;

  public Sat4jSolver(ConjunctiveNormalForm cnf) {
    this.cnf = Objects.requireNonNull(cnf);
  }

  public Interpretation solve() {
    ISolver solver = SolverFactory.newDefault();

    try {
      for (var clause : cnf) {
        var clauseLiteralIds = clause.getLiterals().stream()
            .map(this::getLiteralId)
            .toList();
        solver.addClause(VecInt.of(clauseLiteralIds));
      }
    } catch (ContradictionException e) {
      return null; // -> formula not satisfiable
    }

    int[] model;
    try {
      model = solver.findModel();
    } catch (TimeoutException e) {
      throw new RuntimeException(e);
    }

    if (model == null) {
      return null;
    }

    InterpretationBuilder interpretationBuilder = new InterpretationBuilder();
    for (var literalId : model) {
      var literal = getLiteralFromId(literalId);
      interpretationBuilder.with(literal.getAtom(), literal.isPositive());
    }

    return interpretationBuilder.build();
  }

  private Literal getLiteralFromId(int id) {
    boolean isPositive = id > 0;
    Atom atom = getAtomFromId(id);
    return new Literal(atom, isPositive);
  }

  private Atom getAtomFromId(int id) {
    return idAtoms.get(Math.abs(id));
  }

  private int getLiteralId(Literal literal) {
    int sign = literal.isPositive() ? 1 : -1;
    return sign * getAtomId(literal.getAtom());
  }

  private int getAtomId(Atom atom) {
    Integer id = atomIds.get(atom);
    if (id == null) {
      id = nextId;
      nextId++;
      atomIds.put(atom, id);
      idAtoms.put(id, atom);
    }
    return id;
  }
}
