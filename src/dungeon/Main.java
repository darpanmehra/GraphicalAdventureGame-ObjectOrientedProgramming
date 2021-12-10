package dungeon;

import java.io.InputStreamReader;
import java.util.Random;

import dungeon.controller.Controller;
import dungeon.controller.IController;
import dungeon.controller.IViewController;
import dungeon.controller.ViewController;
import dungeon.model.GameState;
import dungeon.model.IGameState;
import dungeon.view.IView;
import dungeon.view.View;

/**
 * Main class for the Dungeon game.
 */
public class Main {

  /**
   * Main method for the Dungeon game.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {

    if (args.length > 0) { // Text based game

      int dungeonHeight = Integer.parseInt(args[0]);
      int dungeonWidth = Integer.parseInt(args[1]);
      int interConnectivity = Integer.parseInt(args[2]);
      String dungeonType = (args[3]).toLowerCase();
      int treasurePercentage = Integer.parseInt(args[4]);
      int monsterCount = Integer.parseInt(args[5]);

      //Real Random method
      Random rand = new Random();
      //rand.setSeed(50);

      // Create a new dungeon- Initialize the model
      IGameState model = new GameState(dungeonHeight, dungeonWidth, interConnectivity,
              dungeonType, treasurePercentage, monsterCount, rand);

      //Controller
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      IController controller = new Controller(input, output, model);

      //Play game
      controller.playGame();
    } else { // Graphical user interface

      // 1. Create an instance of the model.
      IGameState model = new GameState();
      // 2. Create an instance of the view.
      IView view = new View(model);
      // 3. Create an instance of the controller.
      IViewController controller = new ViewController(model, view);
      controller.playGame(model);
    }

  }
}
