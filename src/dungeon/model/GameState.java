package dungeon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dungeon.model.character.Character;
import dungeon.model.character.Player;
import dungeon.model.directions.Direction;
import dungeon.model.location.ILocation;
import dungeon.model.location.Location;
import dungeon.model.treasure.TreasureType;

/**
 * The GameState class is used to keep track of the state of the game.
 */
public class GameState implements IGameState {

  private boolean isGameSetupComplete;
  private final Grid dungeon;
  private final Character player;

  /**
   * Constructor for the default GameState class for the view.
   */
  public GameState() {
    dungeon = null;
    player = null;
    isGameSetupComplete = false;
  }

  /**
   * Constructor for the GameState class.
   *
   * @param dungeonHeight      The height of the dungeon.
   * @param dungeonWidth       The width of the dungeon.
   * @param interConnectivity  The interconnectivity of the dungeon.
   * @param dungeonType        The type of dungeon.
   * @param treasurePercentage The percentage of treasure in the dungeon.
   * @param random             The random function for the dungeon.
   */
  public GameState(int dungeonHeight, int dungeonWidth, int interConnectivity, String dungeonType,
                   int treasurePercentage, Random random) {
    if (dungeonHeight < 6 || dungeonHeight > 100) {
      throw new IllegalArgumentException("Dungeon width must be between 10 and 100");
    }
    if (dungeonWidth < 6 || dungeonWidth > 100) {
      throw new IllegalArgumentException("Dungeon width must be between 10 and 100");
    }
    if (interConnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity must be between 0 and dungeon size");
    }
    if (!(dungeonType.equalsIgnoreCase("wrapping")
            || dungeonType.equalsIgnoreCase("nonwrapping"))) {
      throw new IllegalArgumentException("Dungeon type must be wrapping or nonwrapping");
    }
    if (treasurePercentage < 0 || treasurePercentage > 100) {
      throw new IllegalArgumentException("Treasure percentage must be between 0 and 100");
    }
    if (random == null) {
      throw new IllegalArgumentException("Random must be specified");
    }

    dungeon = new Grid(dungeonHeight, dungeonWidth, interConnectivity, dungeonType,
            treasurePercentage, random);

    //Create Player and assign start location
    player = new Player("Player", random);
    player.setCurrentLocation(dungeon.getPlayerStartLocation());

    isGameSetupComplete = true;
  }

  /**
   * Constructor for the GameState class.
   *
   * @param dungeonHeight      The height of the dungeon.
   * @param dungeonWidth       The width of the dungeon.
   * @param interConnectivity  The interconnectivity of the dungeon.
   * @param dungeonType        The type of dungeon.
   * @param treasurePercentage The percentage of treasure in the dungeon.
   * @param monsterCount       The number of monsters in the dungeon.
   * @param random             The random function for the dungeon.
   */
  public GameState(int dungeonHeight, int dungeonWidth, int interConnectivity, String dungeonType,
                   int treasurePercentage, int monsterCount, Random random) {
    if (dungeonHeight < 6 || dungeonHeight > 100) {
      throw new IllegalArgumentException("Dungeon width must be between 10 and 100");
    }
    if (dungeonWidth < 6 || dungeonWidth > 100) {
      throw new IllegalArgumentException("Dungeon width must be between 10 and 100");
    }
    if (interConnectivity < 0) {
      throw new IllegalArgumentException("Interconnectivity must be between 0 and dungeon size");
    }
    if (!(dungeonType.equalsIgnoreCase("wrapping")
            || dungeonType.equalsIgnoreCase("nonwrapping"))) {
      throw new IllegalArgumentException("Dungeon type must be wrapping or nonwrapping");
    }
    if (treasurePercentage < 0 || treasurePercentage > 100) {
      throw new IllegalArgumentException("Treasure percentage must be between 0 and 100");
    }
    if (monsterCount < 1) {
      throw new IllegalArgumentException("Monster count must be greater than 0");
    }
    if (random == null) {
      throw new IllegalArgumentException("Random must be specified");
    }

    dungeon = new Grid(dungeonHeight, dungeonWidth, interConnectivity, dungeonType,
            treasurePercentage, monsterCount, random);

    //Create Player and assign start location
    player = new Player("Player", random);
    player.setCurrentLocation(dungeon.getPlayerStartLocation());

    isGameSetupComplete = true;
  }


  @Override
  public boolean isGameSetupComplete() {
    return isGameSetupComplete;
  }

  @Override
  public Character getPlayer() {
    return player;
  }

  @Override
  public ILocation getPlayerStartLocation() {
    return dungeon.getPlayerStartLocation();
  }

  @Override
  public ILocation getPlayerEndLocation() {
    return dungeon.getPlayerEndLocation();
  }

  @Override
  public ILocation getPlayerCurrentLocation() {
    return new Location(player.getCurrentLocation());
  }

  @Override
  public List<ILocation> getVisitedLocations() {
    return player.getLocationVisited();
  }

  @Override
  public String getPlayerArrowsCount() {
    if (player.getTreasures().containsKey(TreasureType.ARROWS)
            && player.getTreasures().get(TreasureType.ARROWS) > 0) {
      return Integer.toString(player.getTreasures().get(TreasureType.ARROWS));
    }
    return "0";
  }

  @Override
  public String getPlayerRubiesCount() {
    if (player.getTreasures().containsKey(TreasureType.RUBIES)
            && player.getTreasures().get(TreasureType.RUBIES) > 0) {
      return Integer.toString(player.getTreasures().get(TreasureType.RUBIES));
    }
    return "0";
  }

  @Override
  public String getPlayerSapphiresCount() {
    if (player.getTreasures().containsKey(TreasureType.SAPPHIRES)
            && player.getTreasures().get(TreasureType.SAPPHIRES) > 0) {
      return Integer.toString(player.getTreasures().get(TreasureType.SAPPHIRES));
    }
    return "0";
  }

  @Override
  public String getPlayerDiamondsCount() {
    if (player.getTreasures().containsKey(TreasureType.DIAMONDS)
            && player.getTreasures().get(TreasureType.DIAMONDS) > 0) {
      return Integer.toString(player.getTreasures().get(TreasureType.DIAMONDS));
    }
    return "0";
  }

  @Override
  public String getPlayerCurrentLocationStatus() {

    List<Direction> doors = new ArrayList<>(
            dungeon.getNeighbours(player.getCurrentLocation()).keySet());
    StringBuilder sb = new StringBuilder();

    int monstersOneLocationAway = getMonstersOneLocationAway();
    int monstersTwoLocationsAway = getMonstersTwoLocationsAway();

    //If pit in the adjacent location
    if (isPitOneLocationAway()) {
      sb.append("The ground is muddy, watch your step.\n");
    }

    //If there are monsters one or two locations away, print them
    if (monstersOneLocationAway == 1 || (monstersOneLocationAway + monstersTwoLocationsAway) > 1) {
      sb.append("You smell something terrible nearby.").append("\n"); //More pungent smell
    } else if (monstersTwoLocationsAway == 1) { // Less pungent smell
      sb.append("You slightly smell something nearby.").append("\n");
    }

    //If there is a killed monster, print it
    if (player.getCurrentLocation().getMonster() != null
            && !player.getCurrentLocation().getMonster().isAlive()) {
      sb.append("You see a dead monster here.").append("\n");
    }

    // If there is a thief here, print it
    if (player.getCurrentLocation().hasThief()) {
      sb.append("You see a thief here and you lose all your treasures.").append("\n");
    }

    sb.append(player);
    if (player.getTreasures().containsKey(TreasureType.ARROWS)
            && player.getTreasures().get(TreasureType.ARROWS) <= 0) {
      sb.append("You are out of arrows. Explore to find more.").append("\n");
    }
    if (player.getCurrentLocation().getTreasure() != null) {
      if (player.getCurrentLocation().getTreasure().toString().length() > 0) {
        sb.append("You find ").append(player.getCurrentLocation().getTreasure().toString())
                .append(" here.\n");
      }
    }
    sb.append("Doors lead to: ").append(doors).append(".\n");

    sb.append("\n");
    return sb.toString();
  }

  @Override
  public String getPlayerCurrentLocationInfo() {
    List<Direction> doors = new ArrayList<>(
            dungeon.getNeighbours(player.getCurrentLocation()).keySet());
    StringBuilder sb = new StringBuilder();

    int monstersOneLocationAway = getMonstersOneLocationAway();
    int monstersTwoLocationsAway = getMonstersTwoLocationsAway();

    //If pit in the adjacent location
    if (isPitOneLocationAway()) {
      sb.append("The ground is muddy, watch your step.\n");
    }

    //If there are monsters one or two locations away, print them
    //If there are monsters one or two locations away, print them
    if (monstersOneLocationAway == 1 || (monstersOneLocationAway + monstersTwoLocationsAway) > 1) {
      sb.append("You smell something terrible nearby.").append("\n"); //More pungent smell
    } else if (monstersTwoLocationsAway == 1) { // Less pungent smell
      sb.append("You slightly smell something nearby.").append("\n");
    }

    //If there is a killed monster, print it
    if (player.getCurrentLocation().getMonster() != null
            && !player.getCurrentLocation().getMonster().isAlive()) {
      sb.append("You see a dead monster here.").append("\n");
    }

    // If there is a thief here, print it
    if (player.getCurrentLocation().hasThief()) {
      sb.append("You see a thief here and you lose all your treasures.").append("\n");
    }

    // If you are in a cave or tunnel
    if (player.getCurrentLocation().isCave()) {
      sb.append("You are in a cave.").append("\n");
    } else {
      sb.append("You are in a tunnel.").append("\n");
    }

    if (player.getTreasures().containsKey(TreasureType.ARROWS)
            && player.getTreasures().get(TreasureType.ARROWS) <= 0) {
      sb.append("You are out of arrows. Explore to find more.").append("\n");
    }
    if (player.getCurrentLocation().getTreasure() != null) {
      if (player.getCurrentLocation().getTreasure().toString().length() > 0) {
        sb.append("You find ").append(player.getCurrentLocation().getTreasure().toString())
                .append(" here.\n");
      }
    }
    sb.append("Doors lead to: ").append(doors).append(".\n");

    sb.append("\n");
    return sb.toString();
  }

  @Override
  public int getSmellStatus() {
    int monstersOneLocationAway = getMonstersOneLocationAway();
    int monstersTwoLocationsAway = getMonstersTwoLocationsAway();

    if (monstersOneLocationAway == 1 || (monstersOneLocationAway + monstersTwoLocationsAway) > 1) {
      return 2; //More pungent smell
    } else if (monstersTwoLocationsAway == 1) {
      return 1; // Less pungent smell
    } else {
      return 0; // No smell
    }
  }

  @Override
  public List<Direction> getAvailableDirectionsFromPlayerPosition() {
    Map<Direction, ILocation> neighbours = dungeon.getNeighbours(player.getCurrentLocation());
    List<Direction> list = new ArrayList<>();
    list.addAll(neighbours.keySet());
    return list;
  }

  @Override
  public ILocation movePlayer(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    ILocation newLocation = dungeon.getNeighbours(player.getCurrentLocation()).get(direction);
    if (newLocation == null) {
      throw new IllegalArgumentException("Cannot move in that direction");
    }
    if (this.isGameOver()) {
      throw new IllegalArgumentException("Game is over");
    }
    player.setCurrentLocation(newLocation);
    return newLocation;
  }

  @Override
  public void pickTreasureFromCurrentLocation(TreasureType treasureType) {
    if (treasureType == null) {
      throw new IllegalArgumentException("Treasure type must be specified");
    }
    if (this.isGameOver()) {
      throw new IllegalArgumentException("Game is over");
    }
    player.pickTreasureFromCurrentLocation(treasureType);
  }

  @Override
  public void shootArrow(Direction direction, int distance) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction must be specified");
    }
    if (distance < 1) {
      throw new IllegalArgumentException("Distance must be greater than 0");
    }
    if (this.isGameOver()) {
      throw new IllegalArgumentException("Game is over");
    }
    player.shootArrow(direction, distance);
  }

  @Override
  public ILocation[][] getDungeon() {
    return dungeon.getDungeonCopy();
  }

  @Override
  public boolean isGameOver() {
    return (player.getCurrentLocation().equals(dungeon.getPlayerEndLocation())
            || !player.isAlive());
  }

  @Override
  public String getGameOverStatus() {
    if (isGameOver()) {
      if (player.isAlive() && player.getCurrentLocation().equals(dungeon.getPlayerEndLocation())) {
        return "You win! You made it to the end.\n";
      } else if (!player.isAlive()) {
        if (player.isDeathByPit()) {
          return "You died by falling into a pit. Better luck next time.\n";
        } else {
          return "Chomp, chomp, chomp, you are eaten by an Otyugh!\nBetter luck next time.\n";
        }
      }
    }
    return "Game not over yet!\n";
  }

  @Override
  public String printPlayerTravelStatus() {
    return player.printTravelStatus();
  }

  //Private methods
  private int getMonstersTwoLocationsAway() {
    List<ILocation> locations = dungeon.getLocationsAway(
            player.getCurrentLocation(), 2);
    int monsters = 0;
    for (ILocation location : locations) {
      if (location.getMonster() != null && location.getMonster().isAlive()) {
        monsters++;
      }
    }
    return monsters;
  }

  private int getMonstersOneLocationAway() {
    List<ILocation> locations = dungeon.getLocationsAway(
            player.getCurrentLocation(), 1);
    int monsters = 0;
    for (ILocation location : locations) {
      if (location.getMonster() != null && location.getMonster().isAlive()) {
        monsters++;
      }
    }
    return monsters;
  }

  private boolean isPitOneLocationAway() {
    List<ILocation> locations = dungeon.getLocationsAway(
            player.getCurrentLocation(), 1);
    for (ILocation location : locations) {
      if (location.hasPit()) {
        return true;
      }
    }
    return false;
  }

}