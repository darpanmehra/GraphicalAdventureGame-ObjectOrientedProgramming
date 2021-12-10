package dungeon.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dungeon.controller.IViewController;


/**
 * The DungeonMouseAdapter class is a MouseAdapter that is used to handle mouse input.
 */
public class DungeonMouseAdapter extends MouseAdapter {

  private IViewController listener;

  public DungeonMouseAdapter(IViewController listener) {
    this.listener = listener;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int row = (e.getY() - DungeonPanel.OFFSET) / DungeonPanel.CELL_SIZE;
    int col = (e.getX() - DungeonPanel.OFFSET) / DungeonPanel.CELL_SIZE;
    listener.handleCellClick(row, col);
  }

}
