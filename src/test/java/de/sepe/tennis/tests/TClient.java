package de.sepe.tennis.tests;

/**
 * TClient.
 * 
 * @author Sebastian Peters
 * @since 16.12.2003
 */
public class TClient {

    private final TPlayerView player;

    /**
     * Constructor.
     * 
     * @throws Exception
     */
    public TClient() throws Exception {
        player = new TPlayerView();
        perform();
    }

    /**
     * Perform.
     */
    private void perform() {
        for (int i = 0; i != 10; ++i) {
            player.animate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        new TClient();
    }
}
