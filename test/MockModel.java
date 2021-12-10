import java.util.List;

import dungeon.model.IGameState;
import dungeon.model.character.Character;
import dungeon.model.directions.Direction;
import dungeon.model.location.ILocation;
import dungeon.model.treasure.TreasureType;

/**
 * This class is used to mock the IGameState interface.
 */
public class MockModel implements IGameState {

  private Appendable output;

  /**
   * Constructs a new MockModel.
   *
   * @param output the output stream to write to.
   */
  public MockModel(Appendable output) {
    this.output = output;
  }

  @Override
  public boolean isGameSetupComplete() {
    try {
      output.append("isGameSetupComplete called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public Character getPlayer() {
    try {
      output.append("getPlayer called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ILocation getPlayerStartLocation() {
    try {
      output.append("getPlayerStartLocation called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ILocation getPlayerEndLocation() {
    try {
      output.append("getPlayerEndLocation called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ILocation getPlayerCurrentLocation() {
    try {
      output.append("getPlayerCurrentLocation called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getPlayerCurrentLocationStatus() {
    try {
      output.append("getPlayerCurrentLocationStatus called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getPlayerCurrentLocationInfo() {
    try {
      output.append("getPlayerCurrentLocationInfo called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Direction> getAvailableDirectionsFromPlayerPosition() {
    try {
      output.append("getAvailableDirectionsFromPlayerPosition called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ILocation movePlayer(Direction direction) {
    try {
      output.append("movePlayer called. Direction: " + direction);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void pickTreasureFromCurrentLocation(TreasureType treasureType) {
    try {
      output.append("pickTreasureFromCurrentLocation called. Treasure: " + treasureType);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void shootArrow(Direction direction, int distance) {
    try {
      output.append("shootArrow called. Direction: " + direction + " Distance: " + distance);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public ILocation[][] getDungeon() {
    try {
      output.append("getDungeon called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ILocation[0][];
  }

  @Override
  public boolean isGameOver() {
    try {
      output.append("isGameOver called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public String getGameOverStatus() {
    try {
      output.append("getGameOverStatus called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String printPlayerTravelStatus() {
    try {
      output.append("printPlayerTravelStatus called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public int getSmellStatus() {
    try {
      output.append("getSmellStatus called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<ILocation> getVisitedLocations() {
    try {
      output.append("getVisitedLocations called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getPlayerArrowsCount() {
    try {
      output.append("getPlayerArrowsCount called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getPlayerRubiesCount() {
    try {
      output.append("getPlayerRubiesCount called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getPlayerSapphiresCount() {
    try {
      output.append("getPlayerSapphiresCount called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getPlayerDiamondsCount() {
    try {
      output.append("getPlayerDiamondsCount called.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
