import tester.*;
import java.util.*;

import javalib.worldimages.Posn;

public class ExamplesMinesweeper {
  // this set is true for revealed
  Cell emptyCell = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell cell0 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell cell1 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell cell2 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell cell3 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell cell4 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell cell5 = new Cell(false, true, false, 0, new ArrayList<Cell>());

  Cell cell6 = new Cell(false, false, false, 0, new ArrayList<Cell>());
  Cell cell7 = new Cell(false, false, false, 0, new ArrayList<Cell>());
  Cell cell8 = new Cell(false, false, false, 0, new ArrayList<Cell>());
  Cell cell9 = new Cell(false, false, false, 0, new ArrayList<Cell>());

  // all cells in this set contain mines
  // set cell 11 to true for flagged in oArder to test minesRemaining
  Cell cell10 = new Cell(true, false, true, 0, new ArrayList<Cell>());
  Cell cell11 = new Cell(true, false, true, 0, new ArrayList<Cell>());
  Cell cell12 = new Cell(true, false, true, 0, new ArrayList<Cell>());

  // cells for testing connect
  Cell conCell0 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell conCell1 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell conCell2 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  Cell conCell3 = new Cell(false, true, false, 0, new ArrayList<Cell>());
  ArrayList<Cell> board2x2 = new ArrayList<Cell>(
      Arrays.asList(this.conCell0, this.conCell1, this.conCell2, this.conCell3));
  ArrayList<Cell> size2x2;

  Random rand1 = new Random(1);
  Random rand2 = new Random(2);
  Random rand3 = new Random(3);

  ArrayList<Cell> gameCells = new ArrayList<Cell>(
      Arrays.asList(this.cell0, this.cell1, this.cell2, this.cell3, this.cell4, this.cell5));

  // ArrayList representing a game that has been won - all mines have been flagged
  ArrayList<Cell> game2 = new ArrayList<Cell>(
      Arrays.asList(this.cell6, this.cell7, this.cell8, this.cell9));

  ArrayList<Cell> game3 = new ArrayList<Cell>(Arrays.asList(this.cell10, this.cell11, this.cell12));

  ArrayUtils utils = new ArrayUtils();

  ArrayList<Integer> testPlacement = new ArrayList<Integer>(Arrays.asList(2, 5));
  ArrayList<Integer> testPlacement2 = new ArrayList<Integer>(Arrays.asList(1, 3));
  ArrayList<Integer> testPlacement3 = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
  ArrayList<Integer> idx0 = new ArrayList<Integer>(Arrays.asList(1, 3, 4));
  ArrayList<Integer> idx4 = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5));

  Game g = new Game(2, 3, 0);

  // initializing the data
  void initConditions() {
    this.emptyCell = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.cell0 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.cell1 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.cell2 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.cell3 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.cell4 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.cell5 = new Cell(false, true, false, 0, new ArrayList<Cell>());

    this.cell6 = new Cell(false, false, false, 0, new ArrayList<Cell>());
    this.cell7 = new Cell(false, false, false, 0, new ArrayList<Cell>());
    this.cell8 = new Cell(false, false, false, 0, new ArrayList<Cell>());
    this.cell9 = new Cell(false, false, false, 0, new ArrayList<Cell>());

    // set cell 11 to true for flagged in order to test minesRemaining
    this.cell10 = new Cell(true, false, true, 0, new ArrayList<Cell>());
    this.cell11 = new Cell(true, false, true, 0, new ArrayList<Cell>());
    this.cell12 = new Cell(true, false, true, 0, new ArrayList<Cell>());

    this.conCell0 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.conCell1 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.conCell2 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.conCell3 = new Cell(false, true, false, 0, new ArrayList<Cell>());
    this.board2x2 = new ArrayList<Cell>(
        Arrays.asList(this.conCell0, this.conCell1, this.conCell2, this.conCell3));

    this.size2x2 = this.utils.connect(this.board2x2, 2, 2, this.rand3);

    this.rand1 = new Random(1);
    this.rand2 = new Random(2);
    this.rand3 = new Random(3);

    this.gameCells = new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell1, this.cell2, this.cell3, this.cell4, this.cell5));
    this.game2 = new ArrayList<Cell>(Arrays.asList(this.cell6, this.cell7, this.cell8, this.cell9));
    this.game3 = new ArrayList<Cell>(Arrays.asList(this.cell10, this.cell11, this.cell12));

    this.utils = new ArrayUtils();

    this.testPlacement = new ArrayList<Integer>(Arrays.asList(2, 5));
    this.testPlacement2 = new ArrayList<Integer>(Arrays.asList(1, 3));
    this.testPlacement3 = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
    this.idx0 = new ArrayList<Integer>(Arrays.asList(1, 3, 4));
    this.idx4 = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5));

    // modifying data

    this.cell0.addNeighbors(this.gameCells, 3, 0);
    this.cell1.addNeighbors(this.gameCells, 3, 1);
    this.cell2.addNeighbors(this.gameCells, 3, 2);
    this.cell3.addNeighbors(this.gameCells, 3, 3);
    this.cell4.addNeighbors(this.gameCells, 3, 4);
    this.cell5.addNeighbors(this.gameCells, 3, 5);

    this.cell0
        .updateNeighbors(new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell3, this.cell4)));
    this.cell1.updateNeighbors(new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell2, this.cell3, this.cell4, this.cell5)));
    this.cell2
        .updateNeighbors(new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell4, this.cell5)));
    this.cell3
        .updateNeighbors(new ArrayList<Cell>(Arrays.asList(this.cell0, this.cell1, this.cell4)));
    this.cell4.updateNeighbors(new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell1, this.cell2, this.cell3, this.cell5)));
    this.cell5
        .updateNeighbors(new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell2, this.cell4)));

    utils.addMines(this.gameCells, this.testPlacement);
    utils.addMines(this.game2, this.testPlacement2);

    this.cell0.updateMinesAround();
    this.cell1.updateMinesAround();
    this.cell2.updateMinesAround();
    this.cell3.updateMinesAround();
    this.cell4.updateMinesAround();
    this.cell5.updateMinesAround();

    this.g = new Game(2, 3, 0);
    this.g.cells = this.gameCells;
  }

  // ArrayUtils Methods Tested
  boolean testAddAllCells(Tester t) {
    this.initConditions();
    this.emptyCell = new Cell(false, false, false, 0, new ArrayList<Cell>());
    return t.checkExpect(new ArrayUtils().addAllCells(new ArrayList<Cell>(), 4),
        new ArrayList<Cell>(
            Arrays.asList(this.emptyCell, this.emptyCell, this.emptyCell, this.emptyCell)));
  }

  boolean testAddMines(Tester t) {
    this.initConditions();
    return t.checkExpect(this.cell2.mine, true) && t.checkExpect(this.cell0.mine, false)
        && t.checkExpect(this.cell5.mine, true);
  }

  void testAddThese(Tester t) {
    this.initConditions();
    t.checkExpect(this.utils.addThese(this.gameCells, this.idx0),
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell3, this.cell4)));
    t.checkExpect(this.utils.addThese(this.gameCells, this.idx4), new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell1, this.cell2, this.cell3, this.cell4, this.cell5)));
  }

  void testCheckIfWon(Tester t) {
    this.initConditions();
    t.checkExpect(new ArrayUtils().checkIfWon(this.game3, 3), true);
    t.checkExpect(new ArrayUtils().checkIfWon(this.gameCells, 2), false);
  }

  void testConnect(Tester t) {
    this.initConditions();
    t.checkExpect(this.size2x2, new ArrayList<Cell>(
        Arrays.asList(this.conCell0, this.conCell1, this.conCell2, this.conCell3)));
    t.checkExpect(this.conCell0.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.conCell1, this.conCell2, this.conCell3)));
    t.checkExpect(this.conCell1.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.conCell0, this.conCell2, this.conCell3)));
    t.checkExpect(this.conCell2.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.conCell0, this.conCell1, this.conCell3)));
    t.checkExpect(this.conCell3.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.conCell0, this.conCell1, this.conCell2)));
  }

  void testCountMines(Tester t) {
    this.initConditions();
    t.checkExpect(new ArrayUtils().countMines(this.gameCells), 2);
    t.checkExpect(new ArrayUtils().countMines(this.game3), 3);
  }

  boolean testGetIndices(Tester t) {
    this.initConditions();
    return t.checkExpect(new ArrayUtils().getIndices(6, 0, 29),
        new ArrayList<Integer>(Arrays.asList(1, 6, 7)))
        && t.checkExpect(new ArrayUtils().getIndices(6, 14, 29),
            new ArrayList<Integer>(Arrays.asList(7, 8, 9, 13, 15, 19, 20, 21)))
        && t.checkExpect(new ArrayUtils().getIndices(6, 11, 29),
            new ArrayList<Integer>(Arrays.asList(4, 5, 10, 16, 17)));
  }

  void testMinePlacement(Tester t) {
    this.initConditions();
    t.checkExpect(this.utils.minePlacement(9, 0, this.rand2), new ArrayList<Integer>());
    t.checkExpect(this.utils.minePlacement(9, 3, this.rand1),
        new ArrayList<Integer>(Arrays.asList(6, 0, 1)));
    t.checkExpect(this.utils.minePlacement(9, 8, this.rand2),
        new ArrayList<Integer>(Arrays.asList(4, 2, 8, 1, 6, 3, 0, 7)));
  }

  void testMinesRemaining(Tester t) {
    this.initConditions();
    utils.addMines(this.game3, this.testPlacement3);
    t.checkExpect(new ArrayUtils().minesRemaining(this.game3, 3), 0);
    t.checkExpect(new ArrayUtils().minesRemaining(this.game2, 2), 2);
  }

  void testShowAllMines(Tester t) {
    this.initConditions();
    new ArrayUtils().showAllMines(this.game2);
    t.checkExpect(this.cell6.revealed, false);
    t.checkExpect(this.cell7.revealed, true);
    t.checkExpect(this.cell8.revealed, false);
    t.checkExpect(this.cell9.revealed, true);
  }

  void testSwap(Tester t) {
    this.initConditions();
    new ArrayUtils().swap(0, 4, this.gameCells);
    t.checkExpect(gameCells.get(4), this.cell0);
    t.checkExpect(gameCells.get(0), this.cell4);

    new ArrayUtils().swap(1, 2, this.game3);
    t.checkExpect(game3.get(1), this.cell11);
    t.checkExpect(game3.get(2), this.cell10);
  }

  // Cell Methods Tested

  void testAddNeighbors(Tester t) {
    this.initConditions();
    t.checkExpect(this.cell0.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell3, this.cell4)));
    t.checkExpect(this.cell4.surroundings, new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell1, this.cell2, this.cell3, this.cell5)));
    t.checkExpect(this.cell2.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell4, this.cell5)));
    t.checkExpect(this.cell5.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell2, this.cell4)));
    t.checkExpect(this.cell1.surroundings, new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell2, this.cell3, this.cell4, this.cell5)));
  }

  void testFlagIt(Tester t) {
    this.initConditions();
    // cell1 is initially not flagged
    this.cell1.flagIt();
    t.checkExpect(this.cell1.flagged, true);

    // cell12 is initially flagged
    this.cell12.flagIt();
    t.checkExpect(this.cell12.flagged, false);
  }

  void testIsMine(Tester t) {
    this.initConditions();
    t.checkExpect(this.cell0.isMine(), false);
    t.checkExpect(this.cell7.isMine(), true);
    t.checkExpect(this.cell9.isMine(), true);
    t.checkExpect(this.cell11.isMine(), true);
  }

  void testMakeMine(Tester t) {
    this.initConditions();
    // cell 10 is already a mine, should not change
    this.cell10.makeMine();
    t.checkExpect(this.cell12.mine, true);

    // this cell is not a mine, should update
    this.cell5.makeMine();
    t.checkExpect(this.cell5.mine, true);
  }

  void testRevealIt(Tester t) {
    this.initConditions();
    // cell 12 is flagged, so it should not be revealed
    this.cell12.revealIt();
    t.checkExpect(this.cell12.revealed, false);

    this.cell6.revealIt();
    t.checkExpect(this.cell6.revealed, true);

    // this cell is already revealed, should not change
    this.cell1.revealIt();
    t.checkExpect(this.cell6.revealed, true);
  }

  boolean testUpdateMinesAround(Tester t) {
    this.initConditions();
    return t.checkExpect(this.cell0.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell3, this.cell4)))
        && t.checkExpect(this.cell0.adjacentMines, 0) && t.checkExpect(this.cell1.adjacentMines, 2)
        && t.checkExpect(this.cell2.adjacentMines, 1);
  }

  void testUpdateNeighbors(Tester t) {
    this.initConditions();
    t.checkExpect(this.cell0.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell3, this.cell4)));
    t.checkExpect(this.cell1.surroundings, new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell2, this.cell3, this.cell4, this.cell5)));
    t.checkExpect(this.cell2.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell4, this.cell5)));
    t.checkExpect(this.cell3.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell0, this.cell1, this.cell4)));
    t.checkExpect(this.cell4.surroundings, new ArrayList<Cell>(
        Arrays.asList(this.cell0, this.cell1, this.cell2, this.cell3, this.cell5)));
    t.checkExpect(this.cell5.surroundings,
        new ArrayList<Cell>(Arrays.asList(this.cell1, this.cell2, this.cell4)));

  }

  // Game methods

  boolean testGetIndex(Tester t) {
    this.initConditions();
    Posn p1 = new Posn(0, 0);
    Posn p2 = new Posn(100, 140);
    return t.checkExpect(this.g.getIndex(p1), 0) && t.checkExpect(this.g.getIndex(p2), 26);
  }

}
