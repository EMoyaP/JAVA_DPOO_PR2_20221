package edu.uoc.pacman.model.entities.characters;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Entity;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public abstract class Character extends Entity implements Movable, Hitable {
    private boolean dead;
    private Direction direction;
    private int duration;
    private Level level;
    private Position startPosition;

    public Character(Position startPosition, Direction direction, Sprite sprite, Level level) {
        super(startPosition, true, sprite);
        setStartPosition(startPosition);
        setDirection(direction);
        setLevel(level);
    }

    public void reset() {
        setPosition(startPosition);
        setDead(false);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    protected int getDuration() {
        return duration;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isDead() {
        return dead;
    }

    private void setDead(boolean dead) {
        this.dead = dead;
    }

    protected Position getStartPosition() {
        return startPosition;
    }

    private void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void kill() {
        setDead(true);
    }

    public void alive() {
        setDead(false);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.getPosition().getX() + "," + getPosition().getY() + "," + getDirection().toString().toUpperCase();
    }

}
