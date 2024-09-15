public class Player extends Item implements Movable {
    private String name;
    private int health;
    private int strength;

    public Player(String name, int x, int y, int health, int strength) {
        super(x, y);
        this.name = name;
        this.health = health;
        this.strength = strength;
    }

    @Override
    public void move(String direction, Maze maze) {
        int newX = getX();
        int newY = getY();

        switch (direction.toLowerCase()) {
            case "w":
                newY--;
                break;
            case "s":
                newY++;
                break;
            case "a":
                newX--;
                break;
            case "d":
                newX++;
                break;
        }

        if (maze.isWalkable(newX, newY)) {
            setX(newX);
            setY(newY);
            maze.setPlayerPosition(newX, newY);
        } else {
            System.out.println("You hit a wall!");
        }
    }

    public void takeDamage(int amount) {
        health -= amount;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

}
