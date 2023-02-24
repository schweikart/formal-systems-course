package art.maxschweik.formsys.propositional.cnf;

import art.maxschweik.formsys.propositional.And;
import art.maxschweik.formsys.propositional.PropositionalFormula;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Immutable
 */
public class ConjunctiveNormalForm implements Iterable<Clause> {
  private final Set<Clause> clauses;

  public ConjunctiveNormalForm(Set<Clause> clauses) {
    this.clauses = clauses;
  }

  public ConjunctiveNormalForm(PropositionalFormula formula) {
    this.clauses = new HashSet<>();
    this.parseClauses(formula);
  }

  private void parseClauses(PropositionalFormula formula) {
    // formula can either be a conjunction of clauses...
    if (formula instanceof And and) {
      parseClauses(and.getLeft());
      parseClauses(and.getRight());

      // ...or a clause itself
    } else {
      clauses.add(new Clause(formula));
    }
  }

  public boolean isEmpty() {
    return this.clauses.isEmpty();
  }

  public Stream<Clause> stream() {
    return this.clauses.stream();
  }

  /**
   * Removes clauses containing the given literal and removes the opposite of the given literal
   * in all remaining clauses.
   * @return a CNF with the changes described above.
   */
  public ConjunctiveNormalForm red(Literal literal) {
    Literal oppositeLiteral = literal.opposite();
    return new ConjunctiveNormalForm(this.clauses.stream()
        // remove clauses containing the literal
        .filter(clause -> !clause.getLiterals().contains(literal))
        // remove opposite of literal in all remaining clauses
        .map(clause -> clause.remove(oppositeLiteral))
        .collect(Collectors.toUnmodifiableSet()));
  }

  public ConjunctiveNormalForm withOneLiteralClause(Literal literal) {
    var newClauses = new HashSet<>(this.clauses);
    newClauses.add(new Clause(Set.of(literal)));
    return new ConjunctiveNormalForm(newClauses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.clauses);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ConjunctiveNormalForm other &&
        this.clauses.equals(other.clauses);
  }

  @Override
  public Iterator<Clause> iterator() {
    return this.clauses.iterator();
  }

  @Override
  public void forEach(Consumer<? super Clause> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<Clause> spliterator() {
    return Iterable.super.spliterator();
  }
}
