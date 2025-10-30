package de.sepe.tennis.remote.data;

import java.io.*;

/**
 * RmiDataTransfer.
 * 
 * @author Sebastian Peters
 * @since 17.12.2003
 */
public class RmiDataTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    public BallData ball;

    public PlayerData initiator;

    public PlayerData contrahent;
}
