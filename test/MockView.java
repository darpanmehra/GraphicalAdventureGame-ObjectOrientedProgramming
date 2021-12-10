import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import dungeon.controller.IViewController;
import dungeon.model.IGameState;
import dungeon.view.DungeonKeyControl;
import dungeon.view.DungeonMouseAdapter;
import dungeon.view.IView;

/**
 * Mock view for testing.
 */
public class MockView extends JFrame implements IView {

  private IGameState model;
  private final Appendable output;

  /**
   * Constructor for MockView.
   *
   * @param model  The model to be used.
   * @param output The output to be used.
   */
  public MockView(IGameState model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  @Override
  public void createView(IViewController controller) {
    try {
      JMenuBar menuBar = new JMenuBar();
      JPanel dungeonPanel = new JPanel();
      JPanel playerInfoPanel = new JPanel();

      this.setJMenuBar(menuBar);
      this.add(dungeonPanel);
      this.add(playerInfoPanel);

      output.append("method called - createView. MenuBar, dungeonPanel & PlayerPanel added.\n");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateModel(IGameState model) {
    this.model = model;
    try {
      output.append("method called - updateModel. Model Starting Location: "
              + this.model.getPlayerStartLocation() + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addClickListener(IViewController listener) {
    JPanel dungeonPanel = new JPanel();
    DungeonMouseAdapter ml = new DungeonMouseAdapter(listener);
    dungeonPanel.addMouseListener(ml);
    try {
      output.append("method called - addClickListener. Mouse click listener " +
              "added to dungeon Panel.\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void addKeyListener(IViewController listener) {
    DungeonKeyControl kl = new DungeonKeyControl(listener);
    this.addKeyListener(kl);
    this.requestFocus();

    try {
      output.append("method called - addKeyListener. DungeonKeyControl Class added to JFrame.\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void refresh() {
    try {
      output.append("method called - refresh. \n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void makeVisible() {
    try {
      output.append("method called - makeVisible. \n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setDefaultScreen(IViewController c) {
    try {
      output.append("method called - setDefaultScreen. \n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
