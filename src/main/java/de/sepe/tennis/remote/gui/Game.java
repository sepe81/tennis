package de.sepe.tennis.remote.gui;

import java.awt.event.*;

import javax.swing.*;

/**
 * The mainframe for any game.
 * 
 * @author Sebastian Peters
 */
@SuppressWarnings("serial")
public abstract class Game extends JFrame {

    /**
     * Constructor.
     */
    public Game(String title) {
        super(title);
        initialize();
        pack();
    }

    /**
     * Initialize.
     */
    protected void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        setResizable(false);
    }

    /**
     * @return MenuBar
     */
    protected JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu mFile = new JMenu("File");

        JMenuItem iExit = new JMenuItem("Exit");
        iExit.addActionListener(createExitActionListener());
        mFile.add(iExit);

        JMenu mGame = new JMenu("Game");

        JMenuItem iAuth = new JMenuItem("Login");
        iAuth.addActionListener(createLoginActionListener());
        mGame.add(iAuth);

        JMenuItem iRegister = new JMenuItem("Register");
        mGame.add(iRegister);

        JMenuItem iJoin = new JMenuItem("Join");
        mGame.add(iJoin);

        JMenuItem iStart = new JMenuItem("Start");
        iStart.addActionListener(createStartActionListener());
        mGame.add(iStart);

        JMenu mHelp = new JMenu("Help");
        JMenuItem iAbout = new JMenuItem("About");
        iAbout.addActionListener(createAboutActionListener());
        mHelp.add(iAbout);

        bar.add(mFile);
        bar.add(mGame);
        bar.add(mHelp);

        return bar;
    }

    /**
     * Start game.
     * 
     * @return ActionListener
     */
    protected abstract ActionListener createStartActionListener();

    /**
     * Exit selection.
     * 
     * @return ActionListener
     */
    protected ActionListener createExitActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.this.dispose();
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
                JOptionPane.showMessageDialog(Game.this, Game.this.getTitle() + "\n\n\u00a9 2003 Sebastian Peters. "
                        + "All Rights Reserved.\n\n");
            }
        };
    }

    /**
     * LoginActionListener
     * 
     * @return ActionListener
     */
    protected abstract ActionListener createLoginActionListener();
}
