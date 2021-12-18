# About

* Degree: Masters in Computer Science
* Course: **CS5010 - Programming Design Paradigms** (Northeastern University)
* Professor: **Clark Freifeld** (Northeastern University)
* Term: *Fall 2021*
* Designed and developed: **Darpan Mehra**

## Overview - Project 5 Graphical Adventure Game

Dungeons are generated at random following some set of constraints resulting in a different network each time the game begins. This is done using Kruskal's algorithm.

A dungeon can be non-wrapping or wrapping in nature. For example, moving to the north from row 0 (at the top) in the grid moves the player to the location in the same column in row 5 (at the bottom). This is an example of a wrapping dungeon.


Each location in the dungeon is connected by exactly one path if the interconnectivity is 0 or there can be more number of paths if the interconnectivity is high.
Treasure is generated at random caves in the dungeon. The percentage of the caves that contain treasure is given by the client.
Arrows are added randomly to the dungeon. The percentage of the arrows that are added is given by the client and appears to be in the same frequency as the treasures.
Monsters are added to random caves in the dungeon. The number of monsters is given by the client.
There is a designated monster at the end location of the dungeon and never appears at the starting location.


A starting point is randomly selected from the dungeon. The player is given 3 arrows to start with.
The player can choose any of the fours actions (move, shoot, pick up, or quit) to take at any point in the game. The player can only move to a location that is connected to the current location. The player can pick up any treasure that is in the same location as the player. If the player enters a cave with a perfectly healthy monster, the player loses the game. A player has 50% of survival if the cave has a injured monster.

The player is given many cues to help them navigate the dungeon.

When a player reaches the end location without being killed, the game is over.

## List of Features

* A non-wrapping dungeon
* A wrapping dungeon
* A dungeon with the interconnectivity of 0 or more
* A dungeon with caves having treasures by specifying the percent of caves to have treasure
* A dungeon with arrows in the same frequency as the treasures
* A dungeon with monsters in random caves by specifying the number of monsters.
* A dungeon with a monster at the end location
* A starting point for the player and assign it to the player
* Get the ending point for the player in the dungeon
* Ability to choose actions (move, shoot, pick up, or quit)
* Ability to move to a location that is connected to the current location
* Ability to pick up any treasure that is in the same location as the player
* Ability to lose the collected treasures if the player encounters a thief in the location
* Ability to lose the game if the player enters a cave with a perfectly healthy monster
* Ability to lose the game if the player falls into the pit
* Ability to survive if the player enters a cave with an injured monster with a 50% chance
* Ability to shoot an arrow in a direction with a specified number of caves
* Ability to navigate the dungeon with the help of the cues
* Ability to quit the game
* Ability to win the game if the player reaches the end location without being killed
* Ability to play in text mode and in graphical mode

## How To Run

### Run the game in Text Mode
Run the jar file.
1. First argument: Dungeon height
2. Second argument: Dungeon width
3. Third argument: Interconnectivity
4. Fourth argument: Dungeon type (wrapping / nonwrapping)
5. Fifth argument: Percent of caves to have treasure
6. Sixth argument: Number of monsters in the dungeon

```bash
cd /YourProjectDirectory/res
java -jar Project5-GraphicalAdventureGame.jar 6 6 0 nonwrapping 20 5
```

### Run the game in GUI Mode

```bash
cd /YourProjectDirectory/res
java -jar Project5-GraphicalAdventureGame.jar
```


## How to Use the Program

### Run the game in Text Mode
1. Initialize the model with the dungeon attributes such as height, width, inter-connectivity, type, treasure percentage, number of monsters and random function
    1. e.g. ```model = new GameState(dungeonHeight, dungeonWidth, interConnectivity, dungeonType, treasurePercentage, monsterCount, rand);```
2. Initialize the controller with a Scanner, an Appendable, and the model
    1. e.g. ```controller = new Controller(scanner, appendable, model)```
3. Run the controller
   1. ```controller.playGame()``` 

### Run the game in GUI Mode
1. Initialize the model, view and controller
    1. e.g. ```model = new GameState();```
    2. e.g. ```view = new View(model);```
    3. e.g. ```controller = new Controller(view, model);```
2. Use the Settings screen at the beginning of the game to set the dungeon attributes such as height, width, inter-connectivity, type, treasure percentage, and the difficulty level.
3. To move the player in the dungeon, use arrow keys (up, down, left, right) or click on the adjacent location.
4. To pick up the treasure, press 'A' for Arrows, 'S' for Sapphire, 'D' for Diamond, or 'R' for Ruby.
5. To shoot the arrow, press number key (1-5) to choose the distance and then use arrow keys (up, down, left, right) to choose the direction while holding the number key.
6. Use the Menu item to Restart the game, Start a new game, or Quit the game.
7. Use the Help item to view the instructions.

Additional Information:

1. Player will be positioned at the starting location
2. At each location, player will be given a choice of actions
3. Player gets cues in the gave if it is close to a monster
   1. e.g. ```You smell something terrible nearby``` 
   2. e.g. ```You slightly smell something nearby```
4. Player can shoot an arrow in the direction specifying the no.of caves
   1. e.g. ```1 + Arrow Key UP to shoot 1 cave up```
5. Player can pick the treasure in the location it is in.
   1. e.g. ```'A' to pick up an arrow```
   2. e.g. ```'S' to pick up a sapphire```
6. Player can move to the adjacent location if it is connected to the current location.
   1. e.g. ```'Arrow Key Up' to move up```
   2. e.g. ```'Arrow Key Down' to move down```
   3. e.g. ```'Arrow Key Left' to move left```
   4. e.g. ```'Arrow Key Right' to move right```
7. Player can quit the game using the Menu item or close the main window.
8. Player wins the game if the player reaches the end location without being killed
   1. e.g. ```Game Over!
      You win! You made it to the end.```
9. Player loses the game if the player enters a cave with a perfectly healthy monster or an injured monster with a 50% chance
   1. e.g. ```Game Over! Chomp, chomp, chomp, you are eaten by an Otyugh! Better luck next time.```
10. Player loses the game if the player falls into the pit
    1. e.g. ```Game Over! You fell into a pit! Better luck next time.``` 

## Description of Examples

### Example runs can be found in the following files in the in res/ folder:

1. Example1.png 
   1. Unvisited Locations are represented by a black square
   2. Visited Locations are represented by an appropriate image
   3. Player is represented by a player image.
   4. Arrow is represented by an arrow image.
   5. Treasures are represented by a treasure chest image.
   6. Dead monsters are represented by a skull image.
   7. Pits in the location.
   8. Thief is represented by a thief image. When a player in the same location as the thief, the player will lose the treasures.
   9. Player falling into the pit and game is over.
   10. Treasures picked up by the player are displayed on the bottom panel.

2. Example2.png
   1. Unvisited Locations are represented by a black square
   2. Visited Locations are represented by an appropriate image
   3. Player is represented by a player image.
   4. Arrow is represented by an arrow image.
   5. Treasures are represented by a treasure chest image.

## Design/Model Changes
1. Added a new interface for the controller for View
2. Added separate classes for dungeon panels and player information panels that implements JPanel
3. Added an interface for the Menu bar and implemented it in the controller

## Assumptions
1. Dungeon height is between 6 and 100
2. Dungeon width is between 6 and 100
3. Interconnectivity cannot be less than 0
4. A big interconnectivity number (greater than maximum paths) means that the dungeon is fully open.
5. Dungeon can be only of two types - wrapping/ non-wrapping
6. Minimum quantity of each type in a treasure is 1 and maximum is 10.
7. If the player reaches the end location without being killed, he wins. But, he does not get to pick any type of treasure in that location as the game ends as soon as he steps into the end location.
8. There are same number thieves as monsters in the dungeon.
9. Thief is never at a start location or end location. There cannot be two thieves in one location.
10. There are same number of pits as monsters in the dungeon.
11. Pit is never at a start location, end location, or locations with monsters. There cannot be two pits in one location.

## Limitations
* Java version 11 or more required.

## Citations
1. https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
2. https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
3. https://www.educba.com/depth-limited-search/
4. https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
5. https://refactoring.guru/design-patterns/command
6. https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html
7. https://stackoverflow.com/questions/2623995/swings-keylistener-and-multiple-keys-pressed-at-the-same-time
8. https://stackoverflow.com/questions/6777135/java-jframe-size-according-to-screen-resolution
