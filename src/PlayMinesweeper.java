import tester.*;
import javalib.impworld.*;

public class PlayMinesweeper {

  // This runs game with a difficulty selection menu
  // Although my implementation of this menu is correct it is laggy for some
  // reason
  // All TA's told me that it must be some issue with a library
  void testBigBangStart(Tester t) {
    World startGame = new Start();
    startGame.bigBang(300, 300, 0.1);
  }

  // This runs the game without difficulty selection, run this to purely enjoy our
  // MineSweeper
  // Restart an Mines left count are implmented

  /*
   * void testBigBangTestGame(Tester t) { Game testGame = new Game(16, 30, 99);
   * testGame.bigBang(testGame.columns*20, testGame.rows*20 + 50, 0.1); }
   */

}
