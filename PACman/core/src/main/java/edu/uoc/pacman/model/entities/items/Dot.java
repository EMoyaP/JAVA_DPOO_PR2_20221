package edu.uoc.pacman.model.entities.items;

import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Dot extends MapItem implements Scorable, Pickable {
    private static final int POINTS = 1;
    private boolean picked = false;

    public Dot(Position position) {
        super(position, true, Sprite.DOT);
    }

    @Override
    public boolean isPicked() {
        return picked;
    }

    @Override
    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    @Override
    public int getPoints() {
        return POINTS;
    }

}
