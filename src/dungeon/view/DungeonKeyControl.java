package dungeon.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dungeon.controller.IViewController;
import dungeon.model.directions.Direction;
import dungeon.model.treasure.TreasureType;

/**
 * This class is responsible for handling keyboard input.
 */
public class DungeonKeyControl implements KeyListener {

  private final IViewController listener;
  private int numberPressed;

  /**
   * Constructor for DungeonKeyControl.
   *
   * @param listener The view controller.
   */
  public DungeonKeyControl(IViewController listener) {
    this.listener = listener;
    this.numberPressed = 0;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // Not needed for now.
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        if (this.numberPressed == 0) {
          listener.movePlayer(Direction.NORTH);
        } else {
          listener.shootArrow(Direction.NORTH, this.numberPressed);
        }
        break;
      case KeyEvent.VK_DOWN:
        if (this.numberPressed == 0) {
          listener.movePlayer(Direction.SOUTH);
        } else {
          listener.shootArrow(Direction.SOUTH, this.numberPressed);
        }
        break;
      case KeyEvent.VK_LEFT:
        if (this.numberPressed == 0) {
          listener.movePlayer(Direction.WEST);
        } else {
          System.out.println("numberPressed: " + this.numberPressed);
          listener.shootArrow(Direction.WEST, this.numberPressed);
        }
        break;
      case KeyEvent.VK_RIGHT:
        if (this.numberPressed == 0) {
          listener.movePlayer(Direction.EAST);
        } else {
          listener.shootArrow(Direction.EAST, this.numberPressed);
        }
        break;
      case KeyEvent.VK_R:
        listener.pickUpTreasure(TreasureType.RUBIES);
        break;
      case KeyEvent.VK_D:
        listener.pickUpTreasure(TreasureType.DIAMONDS);
        break;
      case KeyEvent.VK_S:
        listener.pickUpTreasure(TreasureType.SAPPHIRES);
        break;
      case KeyEvent.VK_A:
        listener.pickUpTreasure(TreasureType.ARROWS);
        break;
      case KeyEvent.VK_1:
        this.numberPressed = 1;
        break;
      case KeyEvent.VK_2:
        this.numberPressed = 2;
        break;
      case KeyEvent.VK_3:
        this.numberPressed = 3;
        break;
      case KeyEvent.VK_4:
        this.numberPressed = 4;
        break;
      case KeyEvent.VK_5:
        this.numberPressed = 5;
        break;
      default:
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.numberPressed = 0;
  }
}
