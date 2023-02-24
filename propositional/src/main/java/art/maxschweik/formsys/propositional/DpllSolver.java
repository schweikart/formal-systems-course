package art.maxschweik.formsys.propositional;

import art.maxschweik.formsys.propositional.cnf.Clause;
import art.maxschweik.formsys.propositional.cnf.ConjunctiveNormalForm;
import art.maxschweik.formsys.propositional.cnf.Literal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class DpllSolver {
  private final ConjunctiveNormalForm cnf;

  public DpllSolver(ConjunctiveNormalForm cnf) {
    this.cnf = Objects.requireNonNull(cnf);
  }

  /**
   *
   * @return null if unsatisfiable, a model interpretation otherwise
   */
  public Interpretation solve() {
    if (this.cnf.isEmpty()) {
      return new Interpretation(Map.of());
    }

    if (this.cnf.stream().anyMatch(Clause::isEmpty)) {
      return null;
    }

    Optional<Clause> oneLiteralClause = this.cnf.stream()
        .filter(clause -> clause.size() == 1)
        .findAny();
    if (oneLiteralClause.isPresent()) {
      Literal thatLiteral = oneLiteralClause.get().getLiterals().stream().findAny().orElseThrow();

      var solution = new DpllSolver(this.cnf.red(thatLiteral)).solve();
      if (solution != null) {
        return solution.withValue(thatLiteral.getAtom(), thatLiteral.isPositive());
      } else {
        return null;
      }
    } else {
      // get any clause, existence was checked above
      Clause anyClause = this.cnf.stream().findAny().orElseThrow();
      // get any literal from that clause, existence (no empty clauses) was verified above
      Literal anyLiteral = anyClause.getLiterals().stream().findAny().orElseThrow();

      var solutionWithLiteral =
          new DpllSolver(this.cnf.withOneLiteralClause(anyLiteral)).solve();
      if (solutionWithLiteral != null) {
        return solutionWithLiteral.withValue(anyLiteral.getAtom(), anyLiteral.isPositive());
      }

      var solutionWithOppositeLiteral =
          new DpllSolver(this.cnf.withOneLiteralClause(anyLiteral.opposite())).solve();
      if (solutionWithOppositeLiteral != null) {
        return solutionWithOppositeLiteral.withValue(anyLiteral.getAtom(), !anyLiteral.isPositive());
      }

      return null;
    }
  }
}
