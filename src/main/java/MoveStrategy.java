/**
 * Interface that describes the movement of the monsters.
 */
public interface MoveStrategy {
    /**
     * Moves monster in the game through time.
     * @param monster
     */
    void moveMonster(Monster monster);
}
