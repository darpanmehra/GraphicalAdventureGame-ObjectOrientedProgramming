package dungeon.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import dungeon.controller.IViewController;

/**
 * Represents the menu for the main view. It has the elements that need to be activated to perform
 * most of the GUI functionality.
 */
public class MenuView extends JMenuBar implements MenuInterface {

  private final JMenuItem newGame;
  private final JMenuItem restart;
  private final JMenuItem exit;

  /**
   * Default constructor builds the menu so the main view can use it.
   */
  public MenuView() {
    super();
    setBackground(new Color(21, 25, 28));

    // Start Game
    JMenu startGame = new JMenu("File");
    startGame.setForeground(Color.white);
    this.add(startGame);
    newGame = new JMenuItem("New Game");
    startGame.add(newGame);
    restart = new JMenuItem("Restart");
    startGame.add(restart);

    // Quit Menu
    JMenu fileMenu = new JMenu("Quit");
    fileMenu.setForeground(Color.white);
    this.add(fileMenu);
    exit = new JMenuItem("Quit Game");
    fileMenu.add(exit);

    // Help Menu
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setForeground(Color.white);
    this.add(helpMenu);
    JMenuItem move = new JMenuItem("Move Player (Arrow Keys)");
    JMenuItem arrow = new JMenuItem("Pick up Arrow (Press 'A')");
    JMenuItem ruby = new JMenuItem("Pick up Ruby (Press 'R')");
    JMenuItem sapphire = new JMenuItem("Pick up Sapphire (Press 'S')");
    JMenuItem diamond = new JMenuItem("Pick up Diamond (Press 'D')");
    JMenuItem shoot = new JMenuItem("Shoot Arrow (Number + Arrow Keys)");
    helpMenu.add(move);
    helpMenu.add(arrow);
    helpMenu.add(ruby);
    helpMenu.add(sapphire);
    helpMenu.add(diamond);
    helpMenu.add(shoot);

  }

  /**
   * Gives the controller over its elements listeners.
   *
   * @param controller the controller to give control too.
   */
  @Override
  public void setFeatures(IViewController controller) {
    newGame.addActionListener(e -> {

      JComboBox<Integer> height = new JComboBox<>();
      JComboBox<Integer> width = new JComboBox<>();
      JComboBox<Integer> interConnectivity = new JComboBox<>();
      JComboBox<Integer> treasurePercentage = new JComboBox<>();
      JComboBox<Integer> monsterCount = new JComboBox<>();

      for (int i = 6; i <= 100; i++) {
        height.addItem(i);
        width.addItem(i);
      }
      for (int i = 1; i <= 100; i++) {
        treasurePercentage.addItem(i);
        monsterCount.addItem(i);
      }
      treasurePercentage.setSelectedItem(View.DEFAULT_TREASURE_PERCENTAGE);
      for (int i = 0; i <= 100000; i++) {
        interConnectivity.addItem(i);
      }

      //Wrapping dungeon type
      ButtonGroup dungeonTypeGroup = new ButtonGroup();
      JRadioButton wrappingYes = new JRadioButton("Yes");
      wrappingYes.setActionCommand("wrapping");
      JRadioButton wrappingNo = new JRadioButton("No");
      wrappingNo.setActionCommand("nonwrapping");
      wrappingNo.setSelected(true);
      dungeonTypeGroup.add(wrappingYes);
      dungeonTypeGroup.add(wrappingNo);

      height.setPreferredSize(new Dimension(100, 30));
      width.setPreferredSize(new Dimension(100, 30));
      interConnectivity.setPreferredSize(new Dimension(100, 30));

      treasurePercentage.setPreferredSize(new Dimension(100, 30));
      monsterCount.setPreferredSize(new Dimension(100, 30));


      JPanel myPanel = new JPanel();

      myPanel.add(new JLabel("Height:"));
      myPanel.add(height);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer

      myPanel.add(new JLabel("Width:"));
      myPanel.add(width);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer

      myPanel.add(new JLabel("Interconnectivity:"));
      myPanel.add(interConnectivity);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer

      myPanel.add(new JLabel("Want a wrapping dungeon?"));
      myPanel.add(wrappingYes);
      myPanel.add(wrappingNo);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer

      myPanel.add(new JLabel("Treasure Percentage:"));
      myPanel.add(treasurePercentage);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer

      myPanel.add(new JLabel("Difficulty:"));
      myPanel.add(monsterCount);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer


      int result = JOptionPane.showConfirmDialog(null, myPanel,
              "Enter game settings", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        int heightInt = (int) height.getSelectedItem();
        int widthInt = (int) width.getSelectedItem();
        int interconnectivityInt = (int) interConnectivity.getSelectedItem();
        String dungeonTypeString = dungeonTypeGroup.getSelection().getActionCommand();
        int treasurePercentageInt = (int) treasurePercentage.getSelectedItem();
        int monsterCountInt = (int) monsterCount.getSelectedItem();

        controller.newGame(heightInt, widthInt, interconnectivityInt, dungeonTypeString,
                treasurePercentageInt, monsterCountInt);
      }
    });

    restart.addActionListener(e -> {
      controller.restartGame();
    });

    exit.addActionListener(e -> System.exit(0));
  }

}
