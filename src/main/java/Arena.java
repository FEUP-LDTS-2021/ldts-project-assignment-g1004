import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Arena {
    private final int height;
    private final int width;
    private Hero hero;
    private List<Wall> walls;
    private List<Platform> platforms;
    private List<Ladder> ladders;
    private List<Coin> coins;
    private Key key;
    private Door door;

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        hero = new Hero(10, 10);
        walls = createWalls();
        platforms = createPlatforms();
        ladders = createLadders();
        coins = createCoins();
        key = new Key(2, 7);
        door = new Door(58, 7);
    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for(int c=0; c < width; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height-1));
        }

        for(int r=0; r < height; r++){
            walls.add(new Wall(0, r));
            walls.add(new Wall(width-1, r));
        }

        return walls;
    }

    private List<Platform> createPlatforms() {
        List<Platform> platforms = new ArrayList<>();

        platforms.add(new Platform(new Position(19, 24), new Position(41, 24)));
        platforms.add(new Platform(new Position(14, 16), new Position(25, 16)));
        platforms.add(new Platform(new Position(35, 16), new Position(46, 16)));
        platforms.add(new Platform(new Position(1, 8), new Position(16, 8)));
        platforms.add(new Platform(new Position(44, 8), new Position(58, 8)));

        return platforms;
    }

    private List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(30, 25), new Position(30, 28)));
        ladders.add(new Ladder(new Position(20, 17), new Position(20, 23)));
        ladders.add(new Ladder(new Position(40, 17), new Position(40, 23)));
        ladders.add(new Ladder(new Position(15, 9), new Position(15, 15)));
        ladders.add(new Ladder(new Position(45, 9), new Position(45, 15)));

        return ladders;
    }

    private List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(40, 28));
        coins.add(new Coin(24, 23));
        coins.add(new Coin(37, 23));
        coins.add(new Coin(25, 15));
        coins.add(new Coin(35, 15));
        coins.add(new Coin(17, 15));
        coins.add(new Coin(42, 15));
        coins.add(new Coin(9, 7));
        coins.add(new Coin(50, 7));

        return coins;
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');

        for(Wall wall : walls)
            wall.draw(screen);

        for (Platform platform : platforms)
            platform.draw(screen);

        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));

        for (Ladder ladder : ladders)
            ladder.draw(screen);

        screen.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));

        for(Coin coin : coins)
            coin.draw(screen);

        key.draw(screen);
        door.draw(screen);
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp    -> moveHero(hero.moveUp());
            case ArrowDown  -> moveHero(hero.moveDown());
            case ArrowLeft  -> moveHero(hero.moveLeft());
            case ArrowRight -> moveHero(hero.moveRight());
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() < width && position.getY() < height && position.getX() >= 0 && position.getY() >= 0)
            return true;

        return false;
    }
}
