package dungeon.controller;

import dungeon.model.IGameState;
import dungeon.model.directions.Direction;
import dungeon.model.treasure.TreasureType;

/**
 * Interface for the view controller.
 */
public interface IViewController {

  /**
   * Starts the game.
   *
   * @param m model.
   */
  void playGame(IGameState m);

  /**
   * Starts a new game.
   *
   * @param heightInt             height of the dungeon.
   * @param widthInt              width of the dungeon.
   * @param interconnectivityInt  interconnectivity of the dungeon.
   * @param dungeonTypeString     type of the dungeon.
   * @param treasurePercentageInt percentage of treasures in the dungeon.
   * @param monsterCountInt       number of monsters in the dungeon.
   */
  void newGame(int heightInt, int widthInt, int interconnectivityInt, String dungeonTypeString,
               int treasurePercentageInt, int monsterCountInt);

  /**
   * Restarts the game with the same parameters.
   */
  void restartGame();

  /**
   * Handle click on the respective location of the screen.
   *
   * @param row row of the location.
   * @param col column of the location.
   */
  void handleCellClick(int row, int col);

  /**
   * Moves the player in the given direction.
   *
   * @param d direction to move.
   */
  void movePlayer(Direction d);

  /**
   * Pick up the treasure in the given location.
   *
   * @param t TreasureType treasure to pick up.
   */
  void pickUpTreasure(TreasureType t);

  /**
   * Shoot the arrow in the given direction and distance.
   *
   * @param d        direction to shoot.
   * @param distance Int distance to shoot.
   */
  void shootArrow(Direction d, int distance);
}
