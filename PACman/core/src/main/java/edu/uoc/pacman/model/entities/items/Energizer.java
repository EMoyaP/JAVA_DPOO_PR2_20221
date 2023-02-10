package edu.uoc.pacman.model.entities.items;

import edu.uoc.pacman.model.entities.Scorable;
import edu.uoc.pacman.model.utils.Position;
import edu.uoc.pacman.model.utils.Sprite;

public class Energizer extends MapItem implements Pickable, Scorable {

    private static final int POINTS = 5;
    private boolean picked = false;

    public Energizer(Position position) {
        super(position, true, Sprite.ENERGIZER);
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
