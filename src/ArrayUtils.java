import java.util.*;


public class ArrayUtils {

  // Generates initial board with given "num" of cells by adding them to
  // accumulator (board)
  // returns board when all cells are generated
  ArrayList<Cell> addAllCells(ArrayList<Cell> board, int num) {
    for (int i = num; i > 0; i--) {
      // we initialize revealed to true for the first part so that the grader can see
      // that our
      // cells are properly linked
      board.add(new Cell(false, false, false, 0, new ArrayList<Cell>()));
    }
    return board;
  }

  // Takes each cell from the board and calls addNeighbors to add all adjacent
  // cells to this given cell
  // adds modified cells to the newBoard, returns newBoard when initial board is
  // over
  ArrayList<Cell> connect(ArrayList<Cell> board, int columns, int numOfMines, Random rand) {

    ArrayList<Cell> newBoard = new ArrayList<Cell>();

    for (int i = 0; i < board.size(); i++) {
      Cell addThis = board.get(i);
      addThis.addNeighbors(board, columns, i);

      newBoard.add(addThis);
    }

    new ArrayUtils().addMines(newBoard,
        new ArrayUtils().minePlacement(board.size(), numOfMines, rand));

    return newBoard;
  }

  // Adds cells with the given indices to the given surroundings,
  // returns new surroundings when list of indices is over
  ArrayList<Cell> addThese(ArrayList<Cell> board, ArrayList<Integer> indices) {
    ArrayList<Cell> surroundings = new ArrayList<Cell>();

    for (Integer i : indices) {
      surroundings.add(board.get(i));
    }
    return surroundings;
  }

  // Generates an ArrayList of indices of cells neighboring to the given index
  ArrayList<Integer> getIndices(int columns, int index, int max) {

    ArrayList<Integer> tmp = new ArrayList<Integer>();

    if (index % columns == 0) {
      tmp = new ArrayList<Integer>(Arrays.asList(index - columns, index - columns + 1, index + 1,
          index + columns, index + columns + 1));
    }
    else if (index % columns == columns - 1) {
      tmp = new ArrayList<Integer>(Arrays.asList(index - columns - 1, index - columns, index - 1,
          index + columns - 1, index + columns));
    }
    else {
      tmp = new ArrayList<Integer>(
          Arrays.asList(index - columns - 1, index - columns, index - columns + 1, index - 1,
              index + 1, index + columns - 1, index + columns, index + columns + 1));
    }

    int size = tmp.size();

    ArrayList<Integer> indices = new ArrayList<Integer>();

    for (int idx = 0; idx < size; idx++) {
      if (tmp.get(idx) >= 0 && tmp.get(idx) <= max) {
        indices.add(tmp.get(idx));
      }
    }
    return indices;
  }

  // Generates an ArrayList of random indices where mines should be placed
  // First generates list of all indices of the given board size and the randomly
  // picks indices from this pool
  // Keeps picking new mine indices until number of mines gets to 0, then returns
  // list of mine indices
  ArrayList<Integer> minePlacement(int size, int numOfMines, Random rand) {
    ArrayList<Integer> pool = new ArrayList<Integer>();
    ArrayList<Integer> placement = new ArrayList<Integer>();

    for (int i = 0; i < size; i++) {
      pool.add(i);
    }
    int maxRand = pool.size();

    for (int i = 0; i < numOfMines; i++) {
      int randomNum = rand.nextInt(maxRand);

      placement.add(pool.get(randomNum));
      this.swap(randomNum, maxRand - 1, pool);
      maxRand = maxRand - 1;
    }
    return placement;
  }

  // Swaps item with index a with item with index b in source array
  <T> void swap(int a, int b, ArrayList<T> source) {
    T item1 = source.get(a);
    T item2 = source.get(b);
    source.set(b, item1);
    source.set(a, item2);

  }

  // Modifies cells that correspond to the given indices of mines to be mines
  ArrayList<Cell> addMines(ArrayList<Cell> board, ArrayList<Integer> placement) {
    for (Integer i : placement) {
      board.get(i).makeMine();
    }
    return board;
  }

  // Counts number of mines in the given cell surroundings
  int countMines(ArrayList<Cell> surroundings) {
    int numOfMines = 0;

    for (Cell c : surroundings) {
      if (c.mine) {
        numOfMines++;
      }
    }
    return numOfMines;
  }

  
  // Goes through the array list of cells and unreveals all mines
  void showAllMines(ArrayList<Cell> board) {
    for (Cell c : board) {
      if (c.mine) {
        c.flagged = false;
        c.revealed = true;
      }
    }
  }

  // Check if game is won
  // Returns true if all unrevealed cells are mines
  boolean checkIfWon(ArrayList<Cell> board, int numOfMines) {
    int i = 0;
    for (Cell c : board) {
      if (!c.revealed) {
        i = i + 1;
      }
    }
    return i == numOfMines;
  }

  // Counts the difference between number of flags set and initial number of mines
  int minesRemaining(ArrayList<Cell> board, int numOfMines) {
    int i = 0;
    for (Cell c : board) {
      if (c.flagged) {
        i = i + 1;
      }
    }
    return numOfMines - i;
  }
}
