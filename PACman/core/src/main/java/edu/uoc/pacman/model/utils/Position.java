package edu.uoc.pacman.model.utils;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public static Position add(Position p1, Position p2) throws NullPointerException {
        if (p1 == null || p2 == null) {
            throw new NullPointerException();
        }
        return new Position(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distance(Position other) {
        if (other == null) {
            return 0;
        }
        double xDiff = x - other.x;
        double yDiff = y - other.y;
        return Math.hypot(xDiff, yDiff);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position position)) return false;

        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return getX() + "," + getY();
    }

}
