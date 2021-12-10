package dungeon.view;

import dungeon.controller.IViewController;
import dungeon.model.IGameState;

/**
 * A view for Dungeon: display the game board and provide visual interface for users.
 */
public interface IView {

  /**
   * Display the game board.
   */
  void createView(IViewController controller);

  /**
   * Update the model with the new game state.
   * @param model the new game state
   */
  void updateModel(IGameState model);

  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */
  void addClickListener(IViewController listener);

  /**
   * Key listener for the view.
   * @param listener the controller
   */
  void addKeyListener(IViewController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Make the default view at start of the game session.
   */
  void setDefaultScreen(IViewController c);
}
