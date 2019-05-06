import java.util.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class Game extends World {

  int rows;
  int columns;
  int numOfMines;
  Random rand;
  ArrayList<Cell> cells;
  boolean gameOver;
  boolean gameWon;
  boolean showMenu;

  Game(int rows, int columns, int numOfMines) {
    this(rows, columns, numOfMines, new Random(),
        new ArrayUtils().addAllCells(new ArrayList<Cell>(), rows * columns));
  }

  Game(int rows, int columns, int numOfMines, Random rand, ArrayList<Cell> cells) {
    this.rows = rows;
    this.columns = columns;
    this.numOfMines = numOfMines;
    this.rand = rand;
    this.cells = new ArrayUtils().connect(cells, columns, numOfMines, rand);
    this.gameOver = false;
    this.gameWon = false;
    this.showMenu = true;
    // mutates the cells to show how many mines surround them
    for (Cell c : this.cells) {
      c.updateMinesAround();
    }
  }

  // Draws the initial game outline
  public WorldScene makeScene() {
    WorldScene base = this.getEmptyScene();
    int textSize = 8 + this.columns / 6;
    int buttonSize = this.columns * 5;

    TextImage winMessage = new TextImage("You Win!", textSize, FontStyle.BOLD, Color.GREEN);
    TextImage loseMessage = new TextImage("GameOver", textSize, FontStyle.BOLD, Color.RED);
    TextImage restart = new TextImage("Restart", textSize, FontStyle.BOLD, Color.BLUE);
    RectangleImage button = new RectangleImage(buttonSize, 50, OutlineMode.OUTLINE, Color.BLACK);
    WorldImage restartButton = new OverlayImage(restart, button);
    ArrayUtils utils = new ArrayUtils();
    TextImage minesRemaining = new TextImage(
        Integer.toString(utils.minesRemaining(cells, numOfMines)), textSize, FontStyle.BOLD,
        Color.RED);

    int idx = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        WorldImage drawThis = this.cells.get(idx).drawCell();

        base.placeImageXY(drawThis, j * (int) drawThis.getWidth() + ((int) drawThis.getWidth() / 2),
            i * (int) drawThis.getHeight() + ((int) drawThis.getHeight() / 2));
        idx++;
        base.placeImageXY(minesRemaining, 13, this.rows * 20 + 25);
        base.placeImageXY(restartButton, this.columns * 20 - buttonSize / 2, this.rows * 20 + 25);
      }
    }
    if (this.gameWon) {
      base.placeImageXY(winMessage, base.width / 2, this.rows * 20 + 25);
    }
    else if (this.gameOver) {
      base.placeImageXY(loseMessage, base.width / 2, this.rows * 20 + 25);
    }
    return base;
  }

  // Handles mouse events
  // Reveals cell if left button is clicked and flags cell if right button is
  // clicked
  // Doesn't do anything if game is over or won, only allows to press start button
  public void onMousePressed(Posn pos, String buttonName) {
    ArrayUtils utils = new ArrayUtils();
    if (pos.x < this.columns * 20 && pos.y < this.rows * 20) {
      if (!this.gameOver && !this.gameWon) {
        if (buttonName.equals("LeftButton")) {
          if (cells.get(getIndex(pos)).isMine()) {
            utils.showAllMines(cells);
            this.gameOver = true;
          }
          else {

            cells.get(getIndex(pos)).revealIt();
            this.gameWon = new ArrayUtils().checkIfWon(cells, numOfMines);

          }
        }
        else {
          cells.get(getIndex(pos)).flagIt();
        }
      }
    }
    else {
      newGame(pos);
    }

  }

  // Generates new mines placement and covers all cells back
  // keeps size of the game the same
  void newGame(Posn pos) {
    if (pos.x > this.columns * 15 && pos.y > this.rows * 20) {
      this.cells = new ArrayUtils().connect(
          new ArrayUtils().addAllCells(new ArrayList<Cell>(), rows * columns), columns, numOfMines,
          rand);
      this.gameOver = false;
      this.gameWon = false;
      // mutates the cells to show how many mines surround them
      for (Cell c : this.cells) {
        c.updateMinesAround();
      }
    }
  }

  // Transform mouse position (Posn) into corresponding cell index
  public int getIndex(Posn pos) {
    return pos.x / 20 + pos.y / 20 * this.columns;
  }
}
