package dungeon.model;

import java.util.List;

import dungeon.model.character.Character;
import dungeon.model.directions.Direction;
import dungeon.model.location.ILocation;
import dungeon.model.treasure.TreasureType;

/**
 * Interface for the game state. It is used by the client to interact with the game state.
 */
public interface IGameState {

  /**
   * Check if the game is set up correctly.
   *
   * @return
   */
  boolean isGameSetupComplete();


  /**
   * Get the character in the game.
   *
   * @return the character in the game.
   */
  Character getPlayer();

  /**
   * Get the start location of the player.
   *
   * @return the start location of the player.
   */
  ILocation getPlayerStartLocation();

  /**
   * Get the end location of the player.
   *
   * @return the end location of the player.
   */
  ILocation getPlayerEndLocation();

  /**
   * Get the player's current location.
   *
   * @return the player's current location.
   */
  ILocation getPlayerCurrentLocation();

  /**
   * Get the player's current location status.
   *
   * @return the player's current location status.
   */
  String getPlayerCurrentLocationStatus();

  /**
   * Get the player's current location description.
   *
   * @return the player's current location description.
   */
  String getPlayerCurrentLocationInfo();

  /**
   * Get the list of available directions to move to from the player's current location.
   *
   * @return the list of available directions to move to from the player's current location.
   */
  List<Direction> getAvailableDirectionsFromPlayerPosition();

  /**
   * Get the current location of the player.
   *
   * @param direction the direction to move to.
   * @return the current location of the player.
   */
  ILocation movePlayer(Direction direction);

  /**
   * Pick up the treasure in the current location.
   *
   * @param treasureType the treasure type to pick up.
   */
  void pickTreasureFromCurrentLocation(TreasureType treasureType);

  /**
   * Shoot the arrow in the given direction and the given distance.
   *
   * @param direction the direction to shoot.
   * @param distance  the distance to shoot (number of caves).
   */
  void shootArrow(Direction direction, int distance);

  /**
   * Get the copy of the game dungeon.
   *
   * @return the copy of the game dungeon.
   */
  ILocation[][] getDungeon();

  /**
   * Get if the game is over or not.
   *
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * Get the status of the game when it is over.
   *
   * @return the status of the game when it is over.
   */
  String getGameOverStatus();

  /**
   * Get the player's travel history.
   *
   * @return the player's travel history.
   */
  String printPlayerTravelStatus();

  /**
   * Get the smell type in the player's location where 0: no smell, 1: slight smell, 2: strong
   * smell.
   *
   * @return the type of smell in the player's location.
   */
  int getSmellStatus();

  /**
   * Get the locations the player has visited.
   *
   * @return the list location the player has visited.
   */
  List<ILocation> getVisitedLocations();

  /**
   * Get the number of arrows the player has.
   *
   * @return the number of arrows the player has.
   */
  String getPlayerArrowsCount();

  /**
   * Get the number of rubies the player has.
   *
   * @return the number of rubies the player has.
   */
  String getPlayerRubiesCount();

  /**
   * Get the number of sapphires the player has.
   *
   * @return the number of sapphires the player has.
   */
  String getPlayerSapphiresCount();

  /**
   * Get the number of diamonds the player has.
   *
   * @return the number of diamonds the player has.
   */
  String getPlayerDiamondsCount();


}
