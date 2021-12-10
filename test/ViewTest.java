import org.junit.Test;
import org.junit.Before;

import java.util.Random;

import dungeon.controller.IViewController;
import dungeon.controller.ViewController;
import dungeon.model.GameState;
import dungeon.model.IGameState;
import dungeon.view.IView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the View class with Mock implementations of it.
 */
public class ViewTest {

  private IGameState model;
  private IView view;
  private IViewController controller;
  private Appendable output;

  @Before
  public void setUp() {
    output = new StringBuilder();
    Random random = new Random();
    random.setSeed(50);
    model = new GameState(6, 6, 0,
            "wrapping", 20, 5, random);
    view = new MockView(model, output);
    controller = new ViewController(model, view);
  }

  @Test
  public void testAddClickListener() {
    view.addClickListener(controller);
    assertTrue(output.toString().contains("method called - addClickListener. Mouse click listener "
            + "added to dungeon Panel.\n"));
  }

  @Test
  public void testAddKeyListener() {
    view.addKeyListener(controller);
    assertTrue(output.toString().contains("method called - addKeyListener. " +
            "DungeonKeyControl Class added to JFrame.\n"));
  }

  @Test
  public void testCreateView() {
    view.createView(controller);
    assertTrue(output.toString().contains("method called - createView. MenuBar, " +
            "dungeonPanel & PlayerPanel added.\n"));
  }

  @Test
  public void testRefresh() {
    view.refresh();
    assertTrue(output.toString().contains("method called - refresh. \n"));
  }

  @Test
  public void testUpdateModel() {
    assertEquals("(5, 0)", model.getPlayerStartLocation().getName());

    Random random = new Random();
    random.setSeed(20);
    model = new GameState(8, 8, 0, "wrapping",
            20, 5, random);
    view.updateModel(model);

    assertTrue(output.toString().contains("method called - updateModel. " +
            "Model Starting Location: (2, 6)\n"));
  }

  @Test
  public void testMakeVisible() {
    view.makeVisible();
    assertTrue(output.toString().contains("method called - makeVisible. \n"));
  }

  @Test
  public void testSetDefaultScreen() {
    view.setDefaultScreen(controller);
    assertTrue(output.toString().contains("method called - setDefaultScreen. \n"));
  }

}
