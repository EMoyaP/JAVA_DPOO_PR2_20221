package edu.uoc.pacman.model.entities.characters.ghosts.chase;

import edu.uoc.pacman.model.entities.characters.ghosts.Ghost;
import edu.uoc.pacman.model.entities.characters.pacman.Pacman;
import edu.uoc.pacman.model.utils.Position;

public class ChasePatrol implements ChaseBehaviour {

    private static final int TILES_OFFSET = 2;
    private static final int VECTOR_INCREASE = 2;

    public ChasePatrol() {
    }

    @Override
    public Position getChasePosition(Ghost ghost) {
        Pacman pacman = ghost.getLevel().getPacman();
        Position firstBlinkyPosition = ghost.getLevel().getBlinky().getPosition();

        return new Position((pacman.getPosition().getX() + (pacman.getDirection().getX() * TILES_OFFSET) - firstBlinkyPosition.getX()) * VECTOR_INCREASE,
                            (pacman.getPosition().getY() + (pacman.getDirection().getY() * TILES_OFFSET) - firstBlinkyPosition.getY()) * VECTOR_INCREASE);
    }

}
