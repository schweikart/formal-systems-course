package art.maxschweik.formsys.propositional;

import art.maxschweik.formsys.propositional.cnf.Clause;
import art.maxschweik.formsys.propositional.cnf.ConjunctiveNormalForm;
import art.maxschweik.formsys.propositional.cnf.Literal;
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
  public boolean solve() {
    if (this.cnf.isEmpty()) {
      return true;
    }

    if (this.cnf.stream().anyMatch(Clause::isEmpty)) {
      return false;
    }

    Optional<Clause> oneLiteralClause = this.cnf.stream()
        .filter(clause -> clause.size() == 1)
        .findAny();
    if (oneLiteralClause.isPresent()) {
      Literal thatLiteral = oneLiteralClause.get().getLiterals().stream().findAny().orElseThrow();
      return new DpllSolver(this.cnf.red(thatLiteral)).solve();
    } else {
      // get any clause, existence was checked above
      Clause anyClause = this.cnf.stream().findAny().orElseThrow();
      // get any literal from that clause, existence (no empty clauses) was verified above
      Literal anyLiteral = anyClause.getLiterals().stream().findAny().orElseThrow();

      return new DpllSolver(this.cnf.withOneLiteralClause(anyLiteral)).solve() ||
          new DpllSolver(this.cnf.withOneLiteralClause(anyLiteral.opposite())).solve();
    }
  }
}
