package dungeon.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.JOptionPane;

import dungeon.controller.IViewController;
import dungeon.model.IGameState;

/**
 * The view class for the dungeon game.
 */
public class View extends JFrame implements IView {

  private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  static final int SCREEN_HEIGHT = screenSize.height;
  static final int SCREEN_WIDTH = screenSize.width;
  static final int DEFAULT_TREASURE_PERCENTAGE = 25;
  private MenuInterface menuBar;
  private PlayerPanel playerPanel;
  private DungeonPanel dungeonPanel;
  private JLabel titleBar;

  private IGameState model;
  private IViewController controller;

  /**
   * The View constructor.
   * @param model The model to be used by the view.
   */
  public View(IGameState model) {
    super("Dungeon Game");
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
    this.controller = null;
    menuBar = new MenuView();
    dungeonPanel = null;
    playerPanel = null;
    titleBar = null;

    setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void addClickListener(IViewController listener) {
    DungeonMouseAdapter ml = new DungeonMouseAdapter(listener);
    dungeonPanel.addMouseListener(ml);
  }

  @Override
  public void addKeyListener(IViewController listener) {
    DungeonKeyControl kl = new DungeonKeyControl(listener);
    this.addKeyListener(kl);
    this.requestFocus();
  }

  @Override
  public void createView(IViewController c) {
    if (c == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }
    this.controller = c;

    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    getContentPane().setBackground(Color.WHITE);

    //Set MenuBar
    menuBar = new MenuView();
    menuBar.setFeatures(controller);
    this.setJMenuBar((JMenuBar) menuBar);

    //Title Bar
    titleBar = new JLabel("Welcome to the Dungeon!");
    titleBar.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleBar.setFont(new Font("Serif", Font.PLAIN, 20));
    titleBar.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

    //Set Dungeon Panel
    dungeonPanel = new DungeonPanel(model);
    //dungeonPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    JScrollPane dungeonScrollPane = new JScrollPane(dungeonPanel);

    //Set Player Panel
    playerPanel = new PlayerPanel(model);
    playerPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

    this.add(titleBar);
    this.add(dungeonScrollPane);
    this.add(playerPanel);
  }

  @Override
  public void refresh() {
    dungeonPanel.updateModel(model);
    playerPanel.updateModel(model);
    this.repaint();
  }

  @Override
  public void updateModel(IGameState model) {
    this.model = model;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setDefaultScreen(IViewController c) {
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
    treasurePercentage.setSelectedItem(DEFAULT_TREASURE_PERCENTAGE);
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

      c.newGame(heightInt, widthInt, interconnectivityInt, dungeonTypeString,
              treasurePercentageInt, monsterCountInt);
    } else {
      System.exit(0);
    }
  }

}
