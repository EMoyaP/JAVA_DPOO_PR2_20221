package edu.uoc.pacman.model.entities.characters.ghosts;

import edu.uoc.pacman.model.Level;
import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.entities.characters.Character;
import edu.uoc.pacman.model.entities.characters.ghosts.chase.ChaseBehaviour;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.entities.characters.pacman.State;
import edu.uoc.pacman.model.utils.Direction;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public abstract class Ghost extends Character implements Scorable {
    protected ChaseBehaviour chaseBehaviour;
    private Behaviour behaviour;
    private Position scatterPosition;

    protected Ghost(Position startPosition, Position scatterPosition, Direction direction, Behaviour behaviour, Sprite sprite, Level level) {
        super(startPosition, direction, sprite, level);
        setBehaviour(behaviour);
        setScatterPosition(scatterPosition);
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        setDuration(behaviour.getDuration());
        this.behaviour = behaviour;
    }

    private void nextBehaviour() {
        setDuration(getDuration() - 1);
        if (getDuration() == 0) {
            if (getBehaviour() == Behaviour.CHASE) {
                setBehaviour(Behaviour.SCATTER);
            } else {
                setBehaviour(Behaviour.CHASE);
            }
        }
    }

    public void reset() {
        setPosition(getStartPosition());
        setDirection(Direction.UP);
        setBehaviour(Behaviour.INACTIVE);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ghost ghost)) return false;
        return this.isDead() == ghost.isDead() && this.getBehaviour().equals(ghost.getBehaviour()) && this.getDirection().equals(ghost.getDirection()) && this.getPosition().equals(ghost.getPosition());
    }

    @Override
    public String toString() {
        return super.toString() + "," + getBehaviour().toString();
    }

    public Position getScatterPosition() {
        return scatterPosition;
    }

    private void setScatterPosition(Position scatterPosition) {
        this.scatterPosition = scatterPosition;
    }

    private Position getTargetPosition() {
        if (getBehaviour() == Behaviour.CHASE) {
            return chaseBehaviour.getChasePosition(this);
        } else if (getBehaviour() == Behaviour.SCATTER || getBehaviour() == Behaviour.FRIGHTENED) {
            return getScatterPosition();
        } else {
            return null;
        }
    }

    @Override
    public void move() {
        Position targetPosition = getTargetPosition();

        if (targetPosition != null && getBehaviour() != Behaviour.INACTIVE) {
            double minDistance = Double.MAX_VALUE;
            Direction newDirection = null;
            Position newPosition = null;

            for (Direction direction : Direction.values()) {
                Position position = new Position((getPosition().getX() + direction.getX()), (getPosition().getY() + direction.getY()));
                if (this.getLevel().isPathable(position) && !direction.opposite().equals(getDirection())) {
                    double distance = position.distance(targetPosition);
                    if (distance <= minDistance) {
                        newDirection = direction;
                        newPosition = position;
                        minDistance = distance;
                    }
                }
            }
            setDirection(newDirection);
            setPosition(newPosition);
            hit();
        }
        nextBehaviour();
    }

    public boolean hit() {
        if (this.getBehaviour() == Behaviour.INACTIVE) {
            return false;
        }
        Pacman pacman = this.getLevel().getPacman();
        if (this.getPosition().equals(pacman.getPosition())) {
            if (this.getBehaviour() == Behaviour.FRIGHTENED) {
                this.kill();
            } else if (pacman.getState() == State.NORMAL) {
                pacman.kill();
            }
            return true;
        }
        return false;
    }

    @Override
    public void kill() {
        super.kill();
        this.getLevel().addPoints(this.getPoints());
        this.reset();

    }
}
