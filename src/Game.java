import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Maze maze = new Maze(15, 15);
        Player player = new Player("Player", 2, 2, 100, 10);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(4, 5, 50, 5));
        monsters.add(new Monster(10, 3, 50, 5));
        monsters.add(new Monster(8, 12, 50, 5));
        Treasure treasure = new Treasure(11, 12);

        maze.setPlayerPosition(player.getX(), player.getY());
        for (Monster monster : monsters) {
            maze.addMonster(monster);
        }
        maze.setTreasure(treasure);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean isRunning = true;

        while (isRunning) {
            maze.display();
            System.out.println("Move using: W A S D");
            String direction = scanner.nextLine();
            player.move(direction, maze);

            // Check if player encounters any monster
            for (Monster monster : monsters) {
                if (player.getX() == monster.getX() && player.getY() == monster.getY()) {
                    player.takeDamage(10);
                    System.out.println("You encountered a monster! You lose 10 health. Current health: " + player.getHealth());

                    if (player.getHealth() <= 0) {
                        System.out.println("You have been defeated by the monsters. Game over.");
                        isRunning = false;
                        break;
                    }
                }
            }

            if (!isRunning) continue;

            // Move each monster
            String[] directions = {"w", "a", "s", "d"};
            for (Monster monster : monsters) {
                String monsterDirection = directions[random.nextInt(directions.length)];
                monster.move(monsterDirection, maze);
                maze.updateMonsterPosition(monster);

                // Check if any monster encounters the player
                if (player.getX() == monster.getX() && player.getY() == monster.getY()) {
                    player.takeDamage(10);
                    System.out.println("You encountered a monster! You lose 10 health. Current health: " + player.getHealth());

                    if (player.getHealth() <= 0) {
                        System.out.println("You have been defeated by the monsters. Game over.");
                        isRunning = false;
                        break;
                    }
                }
            }

            if (player.getX() == treasure.getX() && player.getY() == treasure.getY()) {
                System.out.println("Congratulations! You found the treasure and won the game!");
                isRunning = false;
            }
        }

        scanner.close();
    }
}
