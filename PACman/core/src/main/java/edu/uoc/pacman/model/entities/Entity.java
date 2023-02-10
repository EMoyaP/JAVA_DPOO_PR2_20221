package edu.uoc.pacman.model.entities;

import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public abstract class Entity {
    private boolean pathable;
    private Position position;
    private Sprite sprite;

    protected Entity(Position position, boolean pathable, Sprite sprite) {
        setPosition(position);
        setPathable(pathable);
        setSprite(sprite);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position != null) {
            this.position = position;
        }
    }

    public boolean isPathable() {
        return pathable;
    }

    public void setPathable(boolean pathable) {
        this.pathable = pathable;
    }

    public Sprite getSprite() {
        return sprite;
    }

    protected void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

}
