package edu.uoc.pacman.model.entities.characters.pacman;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Character;
import edu.uoc.pacman.model.entities.characters.ghosts.Behaviour;
import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.items.*;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Pacman extends Character {
    private State state;

    public Pacman(Position startPosition, Direction direction, State state, Level level) {
        super(startPosition, direction, null, level);
        setState(state);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.setDuration(state.getDuration());
        this.state = state;
    }

    private void nextState() {
        if (state == State.EATER || state == State.INVINCIBLE) {
            setDuration(getDuration() - 1);
        }
        if (getDuration() == 0) {
            setState(State.NORMAL);
        }
    }

    @Override
    public void reset() {
        super.reset();
        setState(State.INVINCIBLE);
        setDirection(Direction.UP);
        setPosition(this.getStartPosition());
    }

    @Override
    public void move() {
        Position newPosition = new Position(getPosition().getX() + getDirection().getX(), getPosition().getY() + getDirection().getY());
        if (getLevel().isPathable(newPosition)) {
            setPosition(newPosition);
            eat();
            hit();
        }
        nextState();
    }

    public void setDirection(Direction direction) {
        if (direction != null) {
            super.setDirection(direction);
            this.setSprite(Sprite.valueOf("PACMAN_" + direction.name()));
        }
    }

    private void eat() {
        MapItem item = this.getLevel().getMapItem(this.getPosition());
        if (item instanceof Pickable pickable) {
            if (pickable instanceof Energizer) {
                this.setState(State.EATER);
                this.getLevel().setGhostsFrightened();
            }

            if (pickable instanceof Scorable) {
                this.getLevel().addPoints(((Scorable) pickable).getPoints());
            }

            if (pickable instanceof Life) {
                this.getLevel().increaseLives();
            }

            this.getLevel().removeMapItem(item);
            this.getLevel().addMapItem(new Path(this.getPosition()));
        }
    }

    @Override
    public boolean hit() {
        for (Ghost ghost : this.getLevel().getGhostList()) {
            if (ghost.getPosition().equals(this.getPosition())) {
                if (this.state == State.INVINCIBLE) {
                    return false;
                } else if (ghost.getBehaviour() == Behaviour.FRIGHTENED) {
                    ghost.kill();
                    return true;
                } else if (ghost.getBehaviour() == Behaviour.INACTIVE) {
                    return false;
                } else if (ghost.getBehaviour() == Behaviour.SCATTER || ghost.getBehaviour() == Behaviour.CHASE) {
                    this.kill();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void kill() {
        super.kill();
        getLevel().decreaseLives();
        setState(State.INVINCIBLE);
    }

}
