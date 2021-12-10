package dungeon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dungeon.model.IGameState;
import dungeon.model.directions.Direction;
import dungeon.model.location.ILocation;
import dungeon.model.treasure.TreasureType;

/**
 * The panel that draws the dungeon.
 */
public class DungeonPanel extends JPanel {

  private IGameState model;
  public static final int CELL_SIZE = 80;
  public static final int OFFSET = 40;

  /**
   * Constructor for the Dungeon Panel.
   *
   * @param model The model to be drawn.
   */
  DungeonPanel(IGameState model) {
    this.model = model;
    this.setOpaque(false);
  }

  protected void updateModel(IGameState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    ILocation[][] board = model.getDungeon();
    //Draw the dungeon
    drawDungeon(board, g2);
    this.setPreferredSize(new Dimension(model.getDungeon()[0].length * CELL_SIZE + 2 * OFFSET,
            model.getDungeon().length * CELL_SIZE + 2 * OFFSET));
  }


  //Private method to draw the dungeon
  private void drawDungeon(ILocation[][] board, Graphics2D g2) {
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        ILocation loc = board[r][c];
        BufferedImage image = null;
        try {
          if (loc.isCave()) {
            image = drawCave(loc);
          } else {
            image = drawTunnel(loc);
          }
          g2.drawImage(image, c * CELL_SIZE + OFFSET,
                  r * CELL_SIZE + OFFSET, CELL_SIZE, CELL_SIZE, null);
          //Draw Treasure
          drawTreasures(loc, g2);
          //Draw Arrows
          drawArrows(loc, g2);
          //Draw thief
          drawThief(loc, g2);
          //Draw monsters
          drawMonsters(loc, g2);
          //Draw pits
          drawPits(loc, g2);
          //Draw unvisited locations
          drawUnvisited(loc, g2);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    //Draw the player
    ILocation playerLocation = model.getPlayerCurrentLocation();
    drawPlayer(playerLocation, g2);
  }

  private void drawUnvisited(ILocation loc, Graphics2D g2) {
    List<ILocation> visited = model.getVisitedLocations();
    if (!visited.contains(loc)) {
      g2.setColor(Color.BLACK);
      g2.fillRect(loc.getColCoordinate() * CELL_SIZE + OFFSET,
              loc.getRowCoordinate() * CELL_SIZE + OFFSET, CELL_SIZE, CELL_SIZE);
    }
  }

  private BufferedImage drawCave(ILocation loc) throws IOException {
    Map<Direction, ILocation> neighbours = loc.getNeighbours();
    BufferedImage image = null;
    switch (neighbours.size()) {
      case 1:

        if (neighbours.containsKey(Direction.NORTH)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/N.png"));
        } else if (neighbours.containsKey(Direction.SOUTH)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/S.png"));
        } else if (neighbours.containsKey(Direction.EAST)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/E.png"));
        } else if (neighbours.containsKey(Direction.WEST)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/W.png"));
        }
        break;
      case 3:
        if (neighbours.containsKey(Direction.EAST) && neighbours.containsKey(Direction.WEST)
                && neighbours.containsKey(Direction.NORTH)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/EWN.png"));
        } else if (neighbours.containsKey(Direction.EAST) && neighbours.containsKey(Direction.WEST)
                && neighbours.containsKey(Direction.SOUTH)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/EWS.png"));
        } else if (neighbours.containsKey(Direction.NORTH)
                && neighbours.containsKey(Direction.SOUTH)
                && neighbours.containsKey(Direction.EAST)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/NSE.png"));
        } else if (neighbours.containsKey(Direction.NORTH)
                && neighbours.containsKey(Direction.SOUTH)
                && neighbours.containsKey(Direction.WEST)) {
          image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/NSW.png"));
        }
        break;
      case 4:
        image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/NSEW.png"));
        break;
      default:
    }
    return image;
  }

  private BufferedImage drawTunnel(ILocation loc) throws IOException {
    Map<Direction, ILocation> neighbours = loc.getNeighbours();
    BufferedImage image = null;
    //North south
    if (neighbours.containsKey(Direction.NORTH) && neighbours.containsKey(Direction.SOUTH)) {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/NS.png"));
    }
    //North east
    if (neighbours.containsKey(Direction.NORTH) && neighbours.containsKey(Direction.EAST)) {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/NE.png"));
    }
    //North west
    if (neighbours.containsKey(Direction.NORTH) && neighbours.containsKey(Direction.WEST)) {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/NW.png"));
    }
    //South east
    if (neighbours.containsKey(Direction.SOUTH) && neighbours.containsKey(Direction.EAST)) {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/SE.png"));
    }
    //South west
    if (neighbours.containsKey(Direction.SOUTH) && neighbours.containsKey(Direction.WEST)) {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/SW.png"));
    }
    //East west
    if (neighbours.containsKey(Direction.EAST) && neighbours.containsKey(Direction.WEST)) {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/EW.png"));
    }
    return image;
  }

  private void drawPlayer(ILocation playerLocation, Graphics2D g2) {
    BufferedImage image = null;
    try {
      image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/player.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2.drawImage(image, playerLocation.getColCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
            playerLocation.getRowCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
            CELL_SIZE / 2, CELL_SIZE / 2, null);
    drawSmell(playerLocation, g2);
  }

  private void drawMonsters(ILocation loc, Graphics2D g2) {
    if (loc != null && loc.getMonster() != null) {
      BufferedImage image = null;
      try {
        if (loc.getMonster().getHealth() > 0) {
          image = ImageIO
                  .read(DungeonPanel.class.getResource("/resources/images/monster.png"));
        } else {
          image = ImageIO
                  .read(DungeonPanel.class.getResource("/resources/images/skull.png"));
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      g2.drawImage(image, loc.getColCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              loc.getRowCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              CELL_SIZE / 2, CELL_SIZE / 2, null);
    }
  }

  private void drawThief(ILocation loc, Graphics2D g2) {
    if (loc != null && loc.hasThief()) {
      BufferedImage image = null;
      try {
        image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/thief.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g2.drawImage(image, loc.getColCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              loc.getRowCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              CELL_SIZE / 3, CELL_SIZE / 3, null);
    }
  }

  private void drawTreasures(ILocation loc, Graphics2D g2) {

    if (loc != null && loc.getTreasure() != null
            && ((loc.getTreasure().getTreasure().containsKey(TreasureType.RUBIES)
            || loc.getTreasure().getTreasure().containsKey(TreasureType.SAPPHIRES)
            || loc.getTreasure().getTreasure().containsKey(TreasureType.DIAMONDS)))) {
      BufferedImage image = null;
      try {
        image = ImageIO
                .read(DungeonPanel.class.getResource("/resources/images/treasure.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g2.drawImage(image, loc.getColCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              loc.getRowCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              CELL_SIZE / 4, CELL_SIZE / 4, null);
    }
  }

  private void drawArrows(ILocation loc, Graphics2D g2) {
    if (loc != null && loc.getTreasure() != null
            && loc.getTreasure().getTreasure().containsKey(TreasureType.ARROWS)) {
      BufferedImage image = null;
      try {
        image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/arrow.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g2.drawImage(image, loc.getColCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 3),
              loc.getRowCoordinate() * CELL_SIZE + OFFSET + (OFFSET / 2),
              CELL_SIZE / 2, CELL_SIZE / 2, null);
    }
  }

  private void drawPits(ILocation loc, Graphics2D g2) {
    if (loc.hasPit()) {
      BufferedImage image = null;
      try {
        image = ImageIO.read(DungeonPanel.class.getResource("/resources/images/pit.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g2.drawImage(image, loc.getColCoordinate() * CELL_SIZE + OFFSET ,
              loc.getRowCoordinate() * CELL_SIZE + OFFSET ,
              CELL_SIZE , CELL_SIZE , null);
    }
  }

  private void drawSmell(ILocation playerLocation, Graphics2D g2) {
    BufferedImage image = null;
    try {
      if (model.getSmellStatus() == 2) {
        image = ImageIO
                .read(DungeonPanel.class.getResource("/resources/images/stench02.png"));
      } else if (model.getSmellStatus() == 1) {
        image = ImageIO
                .read(DungeonPanel.class.getResource("/resources/images/stench01.png"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2.drawImage(image, playerLocation.getColCoordinate() * CELL_SIZE + OFFSET,
            playerLocation.getRowCoordinate() * CELL_SIZE + OFFSET,
            CELL_SIZE, CELL_SIZE, null);
  }

}
