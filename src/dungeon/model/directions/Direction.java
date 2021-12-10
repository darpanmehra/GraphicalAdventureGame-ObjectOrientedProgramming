package dungeon.model.directions;

/**
 * A direction in the dungeon a player can move and a location can have its neighbour.
 */
public enum Direction {
  NORTH, SOUTH, EAST, WEST;

  @Override
  public String toString() {
    switch (this) {
      case NORTH:
        return "North";
      case SOUTH:
        return "South";
      case EAST:
        return "East";
      case WEST:
        return "West";
      default:
        return "";
    }
  }
}
