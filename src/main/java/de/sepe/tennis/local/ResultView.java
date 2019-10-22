package de.sepe.tennis.local;

import java.beans.*;

import javax.swing.*;

/**
 * The results of a game.
 * 
 * @author Sebastian Peters
 */
@SuppressWarnings("serial")
public class ResultView extends JPanel implements PropertyChangeListener {

    private Player player1, player2;

    private JLabel name1, name2;
    private JLabel score1, score2;

    /**
     * Constructor.
     */
    public ResultView() {
        initUI();
    }

    /**
     * Constructor.
     * 
     * @param player1 the first player
     * @param player2 the second player
     */
    public ResultView(Player player1, Player player2) {
        initUI();
        setPlayer1(player1);
        setPlayer2(player2);
    }

    /**
     * Init the ui.
     */
    private void initUI() {
        name1 = new JLabel();
        name2 = new JLabel();
        score1 = new JLabel("0 ");
        score2 = new JLabel(" 0");

        add(name1);
        add(score1);
        add(new JLabel(":"));
        add(score2);
        add(name2);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        score1.setText(player1.getScore() + " ");
        score2.setText(" " + player2.getScore());
    }

    /**
     * @param player
     */
    public void setPlayer1(Player player) {
        player1 = player;
        player1.addPropertyChangeListener(this);
        name1.setText(player1.getName());
        score1.setText(player1.getScore() + " ");

    }

    /**
     * @param player
     */
    public void setPlayer2(Player player) {
        player2 = player;
        player2.addPropertyChangeListener(this);
        name2.setText(player2.getName());
        score2.setText(" " + player2.getScore());
    }
}
