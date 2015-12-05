package de.sepe.tennis.local;

import static de.sepe.tennis.local.Court.Type.BLACK;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * The mainframe of the tennis game.
 * 
 * @author Sebastian Peters
 */
@SuppressWarnings("serial")
public final class Tennis extends JFrame {

    /** app title */
    private static final String TITLE = "PingPong";

    /** the court to play on */
    private Court court;

    /** ctrl of player */
    private Controller ctrl1, ctrl2;

    /** players */
    private Player player1, player2;

    /** results */
    private ResultView results;

    /**
     * Constructor.
     */
    public Tennis() {
        super(TITLE);
        initialize();
        pack();
    }

    protected void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        setResizable(false);

        this.court = new Court();
        this.court.setCourtType(BLACK);

        // ctrl for players
        this.ctrl1 = new Controller(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        this.ctrl2 = new Controller(KeyEvent.VK_PAGE_UP, KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_F1, KeyEvent.VK_F2);

        // catch the keyevents
        this.addKeyListener(this.ctrl1);
        this.addKeyListener(this.ctrl2);

        // add the court view
        this.getContentPane().add(this.court, BorderLayout.CENTER);

        results = new ResultView();

        // add the results view
        this.getContentPane().add(results, BorderLayout.SOUTH);

        setVisible(true);
    }

    protected void startGame() {
        court.startGame();
    }

    protected void setResultView(String name) {
        // 2 players
        this.player1 = new Player(name, Color.orange);
        this.player2 = new Player("Michael Stich");

        // set ctrl for each player
        this.player1.setController(this.ctrl1);
        this.player2.setController(this.ctrl2);

        // set players on court
        this.court.setPlayer1(this.player1);
        this.court.setPlayer2(this.player2);

        this.results.setPlayer1(this.player1);
        this.results.setPlayer2(this.player2);
    }

    /**
     * @return MenuBar
     */
    protected JMenuBar createMenuBar() {
        final JMenuBar bar = new JMenuBar();

        final JMenu mFile = new JMenu("File");

        final JMenuItem iExit = new JMenuItem("Exit");
        iExit.addActionListener(createExitActionListener());
        mFile.add(iExit);

        final JMenu mLdap = new JMenu("Game");

        final JMenuItem iAuth = new JMenuItem("Login");
        iAuth.addActionListener(createLoginActionListener());
        mLdap.add(iAuth);

        final JMenu mHelp = new JMenu("Help");
        final JMenuItem iAbout = new JMenuItem("About");
        iAbout.addActionListener(createAboutActionListener());
        mHelp.add(iAbout);

        bar.add(mFile);
        bar.add(mLdap);
        bar.add(mHelp);

        return bar;
    }

    /**
     * Exit selection.
     * 
     * @return ActionListener
     */
    protected ActionListener createExitActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tennis.this.dispose();
                System.exit(0);
            }
        };
    }

    /**
     * Show About Info.
     * 
     * @return ActionListener
     */
    protected ActionListener createAboutActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane
                        .showMessageDialog(Tennis.this, Tennis.TITLE + "\n\n\u00a9 2003 Sebastian Peters. " + "All Rights Reserved.\n\n");
            }
        };
    }

    /**
     * LoginActionListener
     * 
     * @return ActionListener
     */
    protected ActionListener createLoginActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final LoginDialog dia = new LoginDialog(Tennis.this);
                dia.setVisible(true);

                if (!dia.isOk()) {
                    return;
                }

                setResultView(dia.getUser());

                startGame();
            }
        };
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // UIManager.setLookAndFeel(new SkinLookAndFeel());
        } catch (final Exception e) {
            System.err.println("could not set look and feel: " + e.getMessage());
        }

        new Tennis();
    }
}
