import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final ArrayList<ArrayList<String>> layout;
    private int playerX, playerY;
    private final List<Monster> monsters = new ArrayList<>();
    private Treasure treasure;

    public Maze(int width, int height) {
        layout = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add("  "); // Empty cell
            }
            layout.add(row);
        }
        // Add walls to the maze
        addWall(0, 0, width, 1); // Top wall
        addWall(height - 1, 0, width, 1); // Bottom wall
        addWall(0, 0, 1, height); // Left wall
        addWall(0, width - 1, 1, height); // Right wall

        // Add internal walls
        addWall(5, 3, 4, 1); // Vertical wall
        addWall(7, 5, 3, 2); // Horizontal wall
        addWall(2, 11, 4, 2); // Horizontal wall
        addWall(11, 6, 5, 1); // Vertical wall
        addWall(8, 8, 4, 2); // Horizontal wall
        addWall(9, 11, 4, 1); // Horizontal wall
        addWall(9, 14, 4, 2); // Horizontal wall
        addWall(6, 1, 5, 2); // Horizontal wall
        addWall(2, 5, 4, 1); // Horizontal wall
        addWall(5, 9, 2, 2); // Horizontal wall
        addWall(4, 13, 3, 2); // Horizontal wall
        addWall(12, 2, 3, 1); // Horizontal wall
        // Add more walls as needed
    }

    private void addWall(int startX, int startY, int length, int isVertical) {
        for (int i = 0; i < length; i++) {
            int x = startX;
            int y = startY;
            if (isVertical == 1) {
                y += i;
            } else {
                x += i;
            }
            layout.get(y).set(x, "▓ ");
        }
    }

    public boolean isWalkable(int x, int y) {
        // Check if the coordinates are within maze bounds
        if (x < 0 || x >= layout.get(0).size() || y < 0 || y >= layout.size()) {
            return false; // Out of bounds, not walkable
        }
        // If within bounds, check if the position is an obstacle or not
        return !layout.get(y).get(x).equals("▓ ");
    }


    public void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void updateMonsterPosition(Monster monster) {
        // Update monster's position in the maze
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public void display() {
        for (int y = 0; y < layout.size(); y++) {
            for (int x = 0; x < layout.get(y).size(); x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("P ");
                } else if (treasure != null && x == treasure.getX() && y == treasure.getY()) {
                    System.out.print("T ");
                } else {
                    boolean isMonster = false;
                    for (Monster monster : monsters) {
                        if (monster.getX() == x && monster.getY() == y) {
                            System.out.print("M ");
                            isMonster = true;
                            break;
                        }
                    }
                    if (!isMonster) {
                        System.out.print(layout.get(y).get(x));
                    }
                }
            }
            System.out.println();
        }
    }
}
