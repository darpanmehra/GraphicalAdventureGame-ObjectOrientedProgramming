import org.junit.Before;
import org.junit.Test;

import dungeon.controller.IViewController;
import dungeon.controller.ViewController;
import dungeon.controller.commands.ICommand;
import dungeon.controller.commands.Move;
import dungeon.model.IGameState;
import dungeon.model.directions.Direction;
import dungeon.model.treasure.TreasureType;
import dungeon.view.IView;

import static org.junit.Assert.assertTrue;

/**
 * Test Class to test several aspects of the GUI Game.
 */
public class GuiGameTest {

  private IGameState model;
  private IViewController controller;
  private IView view;
  private Appendable out;

  @Before
  public void setUp() {
    out = new StringBuilder();
    model = new MockModel(out);
    view = new MockView(model, out);
    controller = new ViewController(model, view);
  }

  @Test
  public void testPlayGame() {
    controller.playGame(model);

    assertTrue(out.toString().contains("method called - setDefaultScreen. ")); // View method
    assertTrue(out.toString().contains("method called - makeVisible. \n")); // View method
  }

  @Test
  public void testNewGame() {
    controller.newGame(6, 6, 0,
            "wrapping", 20, 5);

    assertTrue(out.toString().contains("method called - createView. MenuBar, dungeonPanel " +
            "& PlayerPanel added."));
    assertTrue(out.toString().contains("method called - addClickListener. Mouse click " +
            "listener added to dungeon Panel."));
    assertTrue(out.toString().contains("method called - addKeyListener. " +
            "DungeonKeyControl Class added to JFrame."));

  }

  @Test
  public void testMovePlayer() {
    ICommand command = new Move(Direction.WEST);
    command.playGame(model);
    view.refresh();

    assertTrue(out.toString().contains("movePlayer called. Direction: West"));
    assertTrue(out.toString().contains("method called - refresh. \n"));

    command = new Move(Direction.NORTH);
    command.playGame(model);
    assertTrue(out.toString().contains("movePlayer called. Direction: North"));

    command = new Move(Direction.EAST);
    command.playGame(model);
    assertTrue(out.toString().contains("movePlayer called. Direction: East"));

    command = new Move(Direction.SOUTH);
    command.playGame(model);
    assertTrue(out.toString().contains("movePlayer called. Direction: South"));
  }

  @Test
  public void testPickUpTreasure() {
    model.pickTreasureFromCurrentLocation(TreasureType.ARROWS);
    view.refresh();

    assertTrue(out.toString().contains("pickTreasureFromCurrentLocation called. Treasure: Arrows"));
    assertTrue(out.toString().contains("method called - refresh. \n"));

    model.pickTreasureFromCurrentLocation(TreasureType.SAPPHIRES);
    assertTrue(out.toString().contains("pickTreasureFromCurrentLocation called. " +
            "Treasure: Sapphires"));

    model.pickTreasureFromCurrentLocation(TreasureType.RUBIES);
    assertTrue(out.toString().contains("pickTreasureFromCurrentLocation called. Treasure: Rubies"));

    model.pickTreasureFromCurrentLocation(TreasureType.DIAMONDS);
    assertTrue(out.toString().contains("pickTreasureFromCurrentLocation called. " +
            "Treasure: Diamonds"));
  }

  @Test
  public void testShootArrow() {
    model.shootArrow(Direction.NORTH, 1);
    view.refresh();
    assertTrue(out.toString().contains("shootArrow called. Direction: North Distance: 1"));
    assertTrue(out.toString().contains("method called - refresh. \n"));

    model.shootArrow(Direction.EAST, 2);
    assertTrue(out.toString().contains("shootArrow called. Direction: East Distance: 2"));

    model.shootArrow(Direction.SOUTH, 3);
    assertTrue(out.toString().contains("shootArrow called. Direction: South Distance: 3"));

    model.shootArrow(Direction.WEST, 4);
    assertTrue(out.toString().contains("shootArrow called. Direction: West Distance: 4"));

    model.shootArrow(Direction.NORTH, 5);
    assertTrue(out.toString().contains("shootArrow called. Direction: North Distance: 5"));
  }
}