import java.util.*;
import java.awt.Color;
import javalib.worldimages.*;

public class Cell {

  boolean mine;
  boolean revealed;
  boolean flagged;
  int adjacentMines;
  ArrayList<Cell> surroundings;

  Cell(boolean mine, boolean revealed, boolean flagged, int adjacentMines,
      ArrayList<Cell> surroundings) {
    this.mine = mine;
    this.revealed = revealed;
    this.flagged = flagged;
    this.adjacentMines = adjacentMines;
    this.surroundings = surroundings;
  }

  // Updates cells surroundings (Used for tests only)
  // EFFECT: mutates cell surroundings to be the given parameter
  void updateNeighbors(ArrayList<Cell> neighbors) {
    this.surroundings = neighbors;
  }

  // Adds all neighbors to the given cell by calling addThese method with a list
  // of neighbors indices as a parameter
  // EFFECT: modifies surrounding field of the given cell
  void addNeighbors(ArrayList<Cell> cells, int columns, int index) {
    this.surroundings = new ArrayUtils().addThese(cells,
        new ArrayUtils().getIndices(columns, index, cells.size() - 1));
  }

  // Makes cell to be the mine
  // EFFECT: sets mine field to true
  void makeMine() {
    this.mine = true;
  }

  boolean isMine() {
    return this.mine;
  }

  // Makes cell to be the mine
  // EFFECT: sets mine field to true
  void revealIt() {
    if (!this.flagged) {
      if (this.adjacentMines == 0) {
        this.revealed = true;
        for (Cell c : this.surroundings) {
          if (!c.revealed) {
            c.revealIt();
          }
        }
      }
      else {
        this.revealed = true;
      }
    }
  }

  // Makes cell to be the mine
  // EFFECT: sets mine field to true
  void flagIt() {
    this.flagged = !this.flagged;
  }

  // Counts and updates the number of mines surrounding the given cell
  // EFFECT: sets adjacentMines field of the given cell to be the output of
  // countMines method
  void updateMinesAround() {
    this.adjacentMines = new ArrayUtils().countMines(this.surroundings);
  }

  // Draws the cell (50x50 gray rectangle). Puts a number on the cell if it has at
  // least one adjacent mine.
  // If cell is mine, red circle will be put onto the cell base
  WorldImage drawCell() {
    Color numberColor = Color.BLACK;
    if (this.adjacentMines == 1) {
      numberColor = Color.BLUE.darker();
    }
    if (this.adjacentMines == 2) {
      numberColor = Color.GREEN.darker();
    }
    if (this.adjacentMines == 3) {
      numberColor = Color.RED.darker();
    }
    RectangleImage cellColor = new RectangleImage(20, 20, OutlineMode.SOLID, Color.GRAY);
    RectangleImage outline = new RectangleImage(20, 20, OutlineMode.OUTLINE, Color.BLACK);
    WorldImage cellCover = new OverlayImage(outline, cellColor);

    WorldImage unCovered = new FrameImage(
        new RectangleImage(20, 20, OutlineMode.SOLID, Color.lightGray));

    if (this.flagged) {
      OverlayImage flagImage = new OverlayImage(
          new EquilateralTriangleImage(7, "Solid", Color.YELLOW), cellCover);
      return flagImage;
    }

    if (!this.revealed) {
      return cellCover;
    }
    if (this.mine) {
      OverlayImage mineImage = new OverlayImage(new CircleImage(5, "Solid", Color.BLACK),
          new OverlayImage(outline, new RectangleImage(20, 20, OutlineMode.SOLID, Color.RED)));
      return mineImage;
    }

    if (this.adjacentMines > 0) {
      OverlayImage numImage = new OverlayImage(
          new TextImage(Integer.toString(this.adjacentMines), 13, FontStyle.BOLD, numberColor),
          unCovered);
      return numImage;
    }
    else {
      return unCovered;
    }
  }
}
