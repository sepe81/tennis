package de.sepe.tennis.remote.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sepe.tennis.remote.Controller;
import de.sepe.tennis.remote.LoginDialog;
import de.sepe.tennis.remote.PlayerView;
import de.sepe.tennis.remote.data.PlayerData;

/**
 * Multiplayer Tennis Game Client.
 * 
 * @author Sebastian Peters
 * @since 15.12.2003
 */
@SuppressWarnings("serial")
public class Tennis extends Game {

    private static final Logger log = LoggerFactory.getLogger(Tennis.class);

    private Controller ctrl;

    private PlayerView player;

    private PlayerView contrahent;

    private Court court;

    private ResultView results;

    /**
     * Constructor.
     */
    public Tennis() {
        super("Multiplayer Tennis");
    }

    @Override
    protected void initialize() {
        super.initialize();

        this.court = new Court(this);

        this.results = new ResultView();

        this.ctrl = new Controller(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

        this.addKeyListener(this.ctrl);

        this.getContentPane().add(this.court, BorderLayout.CENTER);
        this.getContentPane().add(this.results, BorderLayout.SOUTH);
    }

    /**
     * LoginActionListener
     * 
     * @return ActionListener
     */
    @Override
    protected ActionListener createLoginActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final LoginDialog dia = new LoginDialog(Tennis.this);
                dia.setVisible(true);
                if (!dia.isOk()) {
                    return;
                }
            }
        };
    }

    /**
     * Start game.
     * 
     * @return ActionListener
     */
    @Override
    protected ActionListener createStartActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.startGame();
            }
        };
    }

    /**
     * @return
     */
    public PlayerView getPlayer() {
        return player;
    }

    /**
     * @param player
     */
    public void setPlayer(PlayerData player) {
        this.player = new PlayerView(player.name);
        this.player.setData(player);

        this.player.setController(this.ctrl);
        this.results.setName1(player.name);
        this.player.setPosition(new Point2D.Double(20, 20));
        this.player.setDiameter(new Rectangle2D.Double(0, 0, court.getWidth() / 2, court.getHeight()));
    }

    /**
     * @return
     */
    public PlayerView getContrahent() {
        return contrahent;
    }

    /**
     * @param player
     */
    public void setContrahent(PlayerData player) {
        log.info("contrahent set");
        this.contrahent = new PlayerView(player.name);
        this.contrahent.setData(player);
        this.results.setName2(contrahent.getName());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception e) {
            System.err.println("could not set look and feel: " + e.getMessage());
        }
        final Tennis tennis = new Tennis();
        tennis.setVisible(true);
    }

    /**
     * @return
     */
    public ResultView getResults() {
        return results;
    }
}
