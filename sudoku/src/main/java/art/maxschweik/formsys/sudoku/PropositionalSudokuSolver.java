package art.maxschweik.formsys.sudoku;

import art.maxschweik.formsys.propositional.And;
import art.maxschweik.formsys.propositional.Atom;
import art.maxschweik.formsys.propositional.DpllSolver;
import art.maxschweik.formsys.propositional.Not;
import art.maxschweik.formsys.propositional.Or;
import art.maxschweik.formsys.propositional.PropositionalFormula;
import art.maxschweik.formsys.propositional.cnf.ConjunctiveNormalForm;
import java.util.LinkedList;
import java.util.List;

public class PropositionalSudokuSolver {
  private final SudokuBoard board;
  /**
   * atoms[row][col][numberIndex] is true iff the field in (row, col) if filled with number
   */
  private final Atom[][][] atoms = new Atom[9][9][9];
  private final List<PropositionalFormula> formulae = new LinkedList<>();
  private final PropositionalFormula cnfFormula;

  public PropositionalSudokuSolver(SudokuBoard board) {
    this.board = board;

    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        for (int numberIndex = 0; numberIndex < 9; numberIndex++) {
          atoms[row][col][numberIndex] =
              new Atom(String.format("D(%s,%s,%s)", row, col, numberIndex + 1));
        }
      }
    }

    for (int numberIndex = 0; numberIndex < 9; numberIndex++) {
      // rows
      for (int row = 0; row < 9; row++) {
        PropositionalFormula rowCondition = atoms[row][0][numberIndex];
        for (int col = 1; col < 9; col++) {
          rowCondition = new Or(rowCondition, atoms[row][col][numberIndex]);
        }
        formulae.add(rowCondition);
      }

      // cols
      for (int col = 0; col < 9; col++) {
        PropositionalFormula colCondition = atoms[0][col][numberIndex];
        for (int row = 1; row < 9; row++) {
          colCondition = new Or(colCondition, atoms[row][col][numberIndex]);
        }
        formulae.add(colCondition);
      }

      // regions
      for (int regionRow = 0; regionRow < 3; regionRow++) {
        for (int regionCol = 0; regionCol < 3; regionCol++) {
          PropositionalFormula regionCondition = atoms[regionRow * 3][regionCol * 3][numberIndex];
          for (int rowInRegion = 0; rowInRegion < 3; rowInRegion++) {
            for (int colInRegion = 0; colInRegion < 3; colInRegion++) {
              if (rowInRegion == 0 && colInRegion == 0) continue; // already present

              regionCondition = new Or(regionCondition,
                  atoms[regionRow * 3 + rowInRegion][regionCol * 3 + colInRegion][numberIndex]);
            }
          }
        }
      }
    }

    // do not allow multiple numbers on a field
    for (int numberIndex1 = 0; numberIndex1 < 9; numberIndex1++) {
      for (int numberIndex2 = 0; numberIndex2 < numberIndex1; numberIndex2++) {
        for (int row = 0; row < 9; row++) {
          for (int col = 0; col < 9; col++) {
            formulae.add(new Or(
                new Not(atoms[row][col][numberIndex1]),
                new Not(atoms[row][col][numberIndex2])
            ));
            // formulae.add(
                //new Not(new And(atoms[row][col][numberIndex1], atoms[row][col][numberIndex2])));
          }
        }
      }
    }

    // add numbers from source board
    for (int row = 0; row < 9; row++) {
      for (int col = 0; col < 9; col++) {
        SudokuNumber number = board.getNumberAt(row, col);
        if (number != null) {
          formulae.add(atoms[row][col][number.getIndex()]);
        }
      }
    }

    cnfFormula = formulae.stream().reduce(And::new).orElseThrow();
  }

  public SudokuBoard solve() {
    var cnf = new ConjunctiveNormalForm(this.cnfFormula);
    System.out.println(cnf);
    var solver = new DpllSolver(cnf);
    System.out.println(solver.solve());
    return null;
  }
}
