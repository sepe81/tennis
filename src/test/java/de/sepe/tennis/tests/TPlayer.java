package de.sepe.tennis.tests;

import java.util.Random;

/**
 * TPlayer.
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
public class TPlayer {

    private final TPlayerData data = new TPlayerData();

    public TPlayer() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    final Random rnd = new Random();
                    data.x = rnd.nextInt();
                    data.y = rnd.nextInt();
                }
            }
        }.start();
    }

    /**
     * @return
     */
    public TPlayerData getData() {
        return data;
    }

}
