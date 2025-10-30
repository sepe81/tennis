package de.sepe.tennis.remote.data;

import java.io.*;

/**
 * RmiDataTransfer.
 * 
 * @author Sebastian Peters
 * @since 17.12.2003
 */
public class RmiDataTransfer implements Serializable {
    public BallData ball;

    public PlayerData initiator;

    public PlayerData contrahent;
}
