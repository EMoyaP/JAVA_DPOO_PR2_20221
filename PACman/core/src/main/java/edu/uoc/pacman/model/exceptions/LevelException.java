package edu.uoc.pacman.model.exceptions;

public class LevelException extends Exception {
    public static final String GHOSTS_ERROR = "Error message when there isn't any ghost.";
    public static final String PICKABLE_ERROR = "Error message when there are not any dot or energizer.";
    public static final String PARSING_LEVEL_FILE_ERROR = "Error message when there is an error while parsing/loading the level configuration file.";
    public static final String SIZE_ERROR = "Error message when the size of the board does not meet the minimum.";

    public LevelException(String message) {
        super(message);
    }

}
