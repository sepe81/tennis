package de.sepe.tennis.remote.data;

import java.io.*;

/**
 * PlayerData.
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
@SuppressWarnings("serial")
public class PlayerData implements Serializable {

    public String name;

    public double x, y;

    /**
     * Constructor.
     * 
     * @param name
     * @param x
     * @param y
     */
    public PlayerData(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
