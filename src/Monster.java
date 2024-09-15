public class Monster extends Item implements Movable {
    private int health;
    private int strength;

    public Monster(int x, int y, int health, int strength) {
        super(x, y);
        this.health = health;
        this.strength = strength;
    }

    @Override
    public void move(String direction, Maze maze) {
        int newX = getX();
        int newY = getY();

        switch (direction.toLowerCase()) {
            case "w": // Move up
                newY--;
                break;
            case "a": // Move left
                newX--;
                break;
            case "s": // Move down
                newY++;
                break;
            case "d": // Move right
                newX++;
                break;
        }

        if (maze.isWalkable(newX, newY)) {
            setX(newX);
            setY(newY);
        }
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
