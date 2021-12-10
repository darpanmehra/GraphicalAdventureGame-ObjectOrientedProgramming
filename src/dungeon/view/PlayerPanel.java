package dungeon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import dungeon.model.IGameState;

/**
 * The PlayerPanel class is a JPanel that displays the player's information such as the information
 * about the player's current location, and the player's treasures.
 */
public class PlayerPanel extends JPanel {

  private IGameState model;
  private final JLabel playerInfo;
  private final JLabel arrowLabel;
  private final JLabel rubiesLabel;
  private final JLabel sapphireLabel;
  private final JLabel diamondLabel;
  private final String FONT = "Helvetica";
  private final int FONT_SIZE = 18;

  PlayerPanel(IGameState model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
    this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.setAlignmentY(Component.CENTER_ALIGNMENT);
    this.setBackground(Color.WHITE);
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setMaximumSize(new Dimension(View.SCREEN_WIDTH, 100));

    // Player info panel
    JPanel playerInfoPanel = new JPanel();
    playerInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    playerInfoPanel.setBackground(Color.WHITE);
    this.playerInfo = new JLabel();
    this.playerInfo.setFont(new Font(FONT, Font.ITALIC, FONT_SIZE));
    playerInfoPanel.add(playerInfo);

    // Set up PLayer Treasures Info
    JPanel playerTreasureInfoPanel = new JPanel();
    playerTreasureInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
    playerTreasureInfoPanel.setBackground(Color.WHITE);

    ImageIcon arrowImg = new ImageIcon(new ImageIcon(
            getClass().getResource("/resources/images/arrow.png")).getImage()
            .getScaledInstance(60, 60, Image.SCALE_DEFAULT));
    ImageIcon rubyImg = new ImageIcon(new ImageIcon(
            getClass().getResource("/resources/images/ruby.png")).getImage()
            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    ImageIcon sapphireImg = new ImageIcon(new ImageIcon(
            getClass().getResource("/resources/images/sapphire.png")).getImage()
            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    ImageIcon diamondImg = new ImageIcon(new ImageIcon(
            getClass().getResource("/resources/images/diamond.png")).getImage()
            .getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    this.arrowLabel = new JLabel();
    this.arrowLabel.setFont(new Font(FONT, Font.ITALIC, FONT_SIZE));
    this.arrowLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    this.arrowLabel.setIcon(arrowImg);

    this.rubiesLabel = new JLabel();
    this.rubiesLabel.setFont(new Font(FONT, Font.ITALIC, FONT_SIZE));
    this.rubiesLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    this.rubiesLabel.setIcon(rubyImg);

    this.sapphireLabel = new JLabel();
    this.sapphireLabel.setFont(new Font(FONT, Font.ITALIC, FONT_SIZE));
    this.sapphireLabel.setBorder(BorderFactory
            .createEmptyBorder(0, 0, 0, 10));
    this.sapphireLabel.setIcon(sapphireImg);

    this.diamondLabel = new JLabel();
    this.diamondLabel.setFont(new Font(FONT, Font.ITALIC, FONT_SIZE));
    this.diamondLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    this.diamondLabel.setIcon(diamondImg);

    playerTreasureInfoPanel.add(arrowLabel);
    playerTreasureInfoPanel.add(rubiesLabel);
    playerTreasureInfoPanel.add(sapphireLabel);
    playerTreasureInfoPanel.add(diamondLabel);

    //Add the components to the panel
    this.add(playerInfoPanel);
    this.add(playerTreasureInfoPanel);
  }

  protected void updateModel(IGameState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(View.SCREEN_WIDTH, 100);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (model.isGameOver()) {
      playerInfo.setText(model.getGameOverStatus());
    } else {
      playerInfo.setText(model.getPlayerCurrentLocationInfo());
    }

    arrowLabel.setText(model.getPlayerArrowsCount());
    rubiesLabel.setText(model.getPlayerRubiesCount());
    sapphireLabel.setText(model.getPlayerSapphiresCount());
    diamondLabel.setText(model.getPlayerDiamondsCount());

  }
}
