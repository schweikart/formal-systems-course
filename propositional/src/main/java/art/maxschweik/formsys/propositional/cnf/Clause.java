package art.maxschweik.formsys.propositional.cnf;

import art.maxschweik.formsys.propositional.Or;
import art.maxschweik.formsys.propositional.PropositionalFormula;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Clause {
  private final Set<Literal> literals;

  public Clause(PropositionalFormula clauseFormula) {
    this.literals = new HashSet<>();
    this.parseLiterals(clauseFormula);
  }

  public Clause(Set<Literal> literals) {
    this.literals = literals;
  }

  private void parseLiterals(PropositionalFormula literalsFormula) {
    // literals can either be a disjunction of literals...
    if (literalsFormula instanceof Or or) {
      parseLiterals(or.getLeft());
      parseLiterals(or.getRight());

    // ...or a literal itself
    } else {
      literals.add(new Literal(literalsFormula));
    }
  }

  @Override
  public int hashCode() {
    return this.literals.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Clause other &&
        other.literals.equals(this.literals);
  }

  public boolean isEmpty() {
    return literals.isEmpty();
  }

  public int size() {
    return literals.size();
  }

  public Set<Literal> getLiterals() {
    return Set.copyOf(literals);
  }

  public Clause remove(Literal literal) {
    Set<Literal> newLiterals = this.literals.stream()
        .filter(l -> !literal.equals(l))
        .collect(Collectors.toUnmodifiableSet());
    return new Clause(newLiterals);
  }
}
