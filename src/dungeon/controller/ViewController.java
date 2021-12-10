package dungeon.controller;

import java.util.List;
import java.util.Random;

import dungeon.controller.commands.ICommand;
import dungeon.controller.commands.Move;
import dungeon.model.GameState;
import dungeon.model.IGameState;
import dungeon.model.directions.Direction;
import dungeon.model.location.ILocation;
import dungeon.model.treasure.TreasureType;
import dungeon.view.IView;

/**
 * The view controller for the dungeon game.
 */
public class ViewController implements IViewController {

  private int seed;
  private IGameState model;
  private IView view;

  private int dungeonHeight;
  private int dungeonWidth;
  private int interConnectivity;
  private String dungeonType;
  private int treasurePercentage;
  private int monsterCount;

  /**
   * Constructor for the view controller.
   *
   * @param model the model to be used
   * @param view  the view to be used
   */
  public ViewController(IGameState model, IView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or view cannot be null");
    }
    this.model = model;
    this.view = view;
    this.seed = 50;

  }

  @Override
  public void playGame(IGameState m) {
    if (m == null) {
      return;
    }
    view.setDefaultScreen(this);
    view.makeVisible();
  }

  @Override
  public void newGame(int heightInt, int widthInt, int interconnectivityInt,
                      String dungeonTypeString, int treasurePercentageInt, int monsterCountInt) {
    if (heightInt < 1 || widthInt < 1 || interconnectivityInt < 0 || treasurePercentageInt < 0
            || monsterCountInt < 0) {
      return;
    }
    seed = new Random().nextInt(100);

    Random rand = new Random();
    rand.setSeed(seed);

    this.dungeonHeight = heightInt;
    this.dungeonWidth = widthInt;
    this.interConnectivity = interconnectivityInt;
    this.dungeonType = dungeonTypeString;
    this.treasurePercentage = treasurePercentageInt;
    this.monsterCount = monsterCountInt;

    if (model.isGameSetupComplete()) {
      model = new GameState(heightInt, widthInt, interconnectivityInt, dungeonTypeString,
              treasurePercentageInt, monsterCountInt, rand);
      view.updateModel(model);
      view.refresh();
    } else {

      model = new GameState(heightInt, widthInt, interconnectivityInt, dungeonTypeString,
              treasurePercentageInt, monsterCountInt, rand);
      view.updateModel(model);

      view.createView(this);
      view.addClickListener(this);
      view.addKeyListener(this);
    }
  }

  @Override
  public void restartGame() {
    Random rand = new Random();
    rand.setSeed(seed);
    model = new GameState(dungeonHeight, dungeonWidth, interConnectivity, dungeonType,
            treasurePercentage, monsterCount, rand);
    view.updateModel(model);
    view.refresh();
  }

  @Override
  public void handleCellClick(int row, int col) {
    if (row < 0 || col < 0) {
      return;
    }
    try {
      movePlayerHelper(row, col);
      view.refresh();
    } catch (IllegalArgumentException e) {
      // do nothing
    } catch (IllegalStateException e) {
      // do nothing
    }
  }

  @Override
  public void movePlayer(Direction dir) {
    if (dir == null) {
      return;
    }
    try {
      ICommand move = new Move(dir);
      move.playGame(model);
      view.refresh();
    } catch (IllegalArgumentException e) {
      // do nothing
    } catch (Exception e) {
      // do nothing
    }
  }

  @Override
  public void pickUpTreasure(TreasureType treasure) {
    if (treasure == null) {
      return;
    }
    try {
      model.pickTreasureFromCurrentLocation(treasure);
      view.refresh();
    } catch (IllegalArgumentException e) {
      // do nothing
    } catch (IllegalStateException e) {
      // do nothing
    } catch (Exception e) {
      // do nothing
    }
  }

  @Override
  public void shootArrow(Direction direction, int distance) {
    if (direction == null || distance < 1) {
      return;
    }
    try {
      model.shootArrow(direction, distance);
      view.refresh();
    } catch (IllegalArgumentException e) {
      // do nothing
    } catch (IllegalStateException e) {
      // do nothing
    } catch (Exception e) {
      // do nothing
    }
  }

  private void movePlayerHelper(int row, int col) {
    Direction dir;
    List<Direction> availableDirections = model.getAvailableDirectionsFromPlayerPosition();
    ILocation loc = model.getPlayerCurrentLocation();
    if (row == loc.getRowCoordinate() - 1 && col == loc.getColCoordinate()) {
      dir = Direction.NORTH;
      if (availableDirections.contains(dir)) {
        this.movePlayer(dir);
      }
    } else if (row == loc.getRowCoordinate() + 1 && col == loc.getColCoordinate()) {
      dir = Direction.SOUTH;
      if (availableDirections.contains(dir)) {
        this.movePlayer(dir);
      }
    } else if (row == loc.getRowCoordinate() && col == loc.getColCoordinate() + 1) {
      dir = Direction.EAST;
      if (availableDirections.contains(dir)) {
        this.movePlayer(dir);
      }
    } else if (row == loc.getRowCoordinate() && col == loc.getColCoordinate() - 1) {
      dir = Direction.WEST;
      if (availableDirections.contains(dir)) {
        this.movePlayer(dir);
      }
    }

  }

}
