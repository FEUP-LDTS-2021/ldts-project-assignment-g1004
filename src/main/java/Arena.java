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
        key = new Key(2, 3);
        door = new Door(58, 3);
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

        platforms.add(new Platform(new Position(18, 16), new Position(34, 16)));
        platforms.add(new Platform(new Position(6, 13), new Position(48, 13)));
        platforms.add(new Platform(new Position(4, 10), new Position(17, 10)));
        platforms.add(new Platform(new Position(26, 10), new Position(50, 10)));
        platforms.add(new Platform(new Position(10, 7), new Position(30, 7)));
        platforms.add(new Platform(new Position(40, 7), new Position(52, 7)));
        platforms.add(new Platform(new Position(1, 4), new Position(25, 4)));
        platforms.add(new Platform(new Position(48, 4), new Position(58, 4)));

        return platforms;
    }

    private List<Ladder> createLadders() {
        List<Ladder> ladders = new ArrayList<>();

        ladders.add(new Ladder(new Position(20, 16), new Position(20, 18)));
        ladders.add(new Ladder(new Position(30, 13), new Position(30, 15)));
        ladders.add(new Ladder(new Position(16, 10), new Position(16, 12)));
        ladders.add(new Ladder(new Position(11, 7), new Position(11, 9)));
        ladders.add(new Ladder(new Position(28, 7), new Position(28, 9)));
        ladders.add(new Ladder(new Position(41, 7), new Position(41, 9)));
        ladders.add(new Ladder(new Position(23, 4), new Position(23, 6)));
        ladders.add(new Ladder(new Position(50, 4), new Position(50, 6)));

        return ladders;
    }

    private List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();

        coins.add(new Coin(40, 18));
        coins.add(new Coin(18, 15));
        coins.add(new Coin(31, 15));
        coins.add(new Coin(8, 12));
        coins.add(new Coin(37, 12));
        coins.add(new Coin(32, 9));
        coins.add(new Coin(44, 6));
        coins.add(new Coin(12, 3));

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
