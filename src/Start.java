import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class Start extends World {

  // Produces worldscene with 3 buttons on it corresponding to Easy, Medium and
  // Hard levels
  public WorldScene makeScene() {
    WorldScene base = this.getEmptyScene();
    TextImage message = new TextImage("Difficulty Level:", 13, FontStyle.BOLD, Color.BLACK);
    TextImage easy = new TextImage("EASY: 9 x 9, 10 mines", 13, FontStyle.BOLD, Color.GREEN);
    TextImage medium = new TextImage("MEDIUM: 16 x 16, 40 mines", 13, FontStyle.BOLD, Color.ORANGE);
    TextImage hard = new TextImage("HARD: 16 x 30, 99 mines", 13, FontStyle.BOLD, Color.RED);
    RectangleImage button = new RectangleImage(200, 30, OutlineMode.OUTLINE, Color.GRAY);
    WorldImage easyButton = new OverlayImage(easy, button);
    WorldImage mediumButton = new OverlayImage(medium, button);
    WorldImage hardButton = new OverlayImage(hard, button);

    base.placeImageXY(message, 150, 70);
    base.placeImageXY(easyButton, 150, 100);
    base.placeImageXY(mediumButton, 150, 150);
    base.placeImageXY(hardButton, 150, 200);

    return base;

  }

  // Checks which difficulty level is pressed and run bigbang on correspondent
  // instance of the Game class
  public void onMousePressed(Posn pos) {
    Game easy = new Game(9, 9, 10);
    Game medium = new Game(16, 16, 40);
    Game hard = new Game(16, 30, 99);
    if (pos.x > 50 && pos.x < 250) {
      if (pos.y >= 100 && pos.y <= 130) {
        easy.bigBang(180, 230);
      }
      if (pos.y >= 150 && pos.y <= 180) {
        medium.bigBang(320, 370);
      }
      if (pos.y >= 200 && pos.y <= 230) {
        hard.bigBang(600, 370);
      }
    }
  }

}
