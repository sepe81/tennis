package de.sepe.tennis.remote.gui;

import javax.swing.*;

/**
 * The results of a game.
 * 
 * @author Sebastian Peters
 */
@SuppressWarnings("serial")
public class ResultView extends JPanel {

    private JLabel name1, name2;
    private JLabel score1, score2;

    /**
     * Constructor.
     */
    public ResultView() {
        initUI();
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

    public void setName1(String name) {
        name1.setText(name);
    }

    public void setName2(String name) {
        name2.setText(name);
    }

    public void setScore1(int s) {
        score1.setText(s + " ");
    }

    public void setScore2(int s) {
        score2.setText(" " + s);
    }
}
