import java.io.IOException;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;


import static java.lang.System.exit;

public class Arena {

    private int height;
    private int width;
    private Hero hero = new Hero(10,10);

    public Screen screen;

    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
        Hero hero = new Hero(10, 10);
    }

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case EOF:
                exit(0);
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            default:
                if (key.getCharacter() == 'q') {
                    screen.close();
                }
        }
    }

    public void draw(Screen screen) throws IOException {
        hero.draw(screen);
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if(position.getX() < width && position.getY() < height && position.getX() >= 0 && position.getY() >= 0) return true;
        return false;
    }

}
