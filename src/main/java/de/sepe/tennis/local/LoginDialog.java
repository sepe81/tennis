package de.sepe.tennis.local;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Register user to server.
 * 
 * @author Sebastian Peters
 * @since 15.12.2003
 */
@SuppressWarnings("serial")
public final class LoginDialog extends JDialog {

    private JLabel labUser = new JLabel("User:");
    private JLabel labPassword = new JLabel("Password:");

    private JTextField txtUser = new JTextField(12);
    private JPasswordField pwdPassword = new JPasswordField(12);

    private JButton butLogin = new JButton("Login");

    /** pressed ok? */
    private boolean ok = false;

    /**
     * Constructor.
     * 
     * @param owner owner of dialog
     */
    public LoginDialog(JFrame owner) {
        super(owner, "Login", true);
        this.setLocationRelativeTo(owner);
        initialize();
    }

    /**
     * Initialize Gui.
     */
    private void initialize() {
        this.setResizable(false);

        double sizes[][] = { { 5, TableLayoutConstants.PREFERRED, 5, TableLayoutConstants.FILL, 5 },
                { 5, TableLayoutConstants.PREFERRED, 5, TableLayoutConstants.PREFERRED, 5, TableLayoutConstants.PREFERRED, 5 } };
        this.getContentPane().setLayout(new TableLayout(sizes));
        this.getContentPane().add(labUser, "1, 1, r");
        this.getContentPane().add(labPassword, "1, 3, r");

        this.getContentPane().add(txtUser, "3, 1, r");
        this.getContentPane().add(pwdPassword, "3, 3, r");

        this.getContentPane().add(butLogin, "3, 5, r");

        butLogin.addActionListener(createLoginActionListener());

        // register KeyboardActions since jdk1.4
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "actExit");
        am.put("actExit", new AbstractAction() {
            public void actionPerformed(ActionEvent evt) {
                LoginDialog.this.ok = false;
                LoginDialog.this.dispose();
            }
        });

        this.pack();
    }

    /**
     * React on login pressed.
     * 
     * @return ActionListener
     */
    private ActionListener createLoginActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginDialog.this.ok = true;
                LoginDialog.this.dispose();
            }
        };
    }

    /**
     * Has ok been pressed?
     * 
     * @return true, if ok has been pressed.
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Get user name.
     * 
     * @return name
     */
    public String getUser() {
        return txtUser.getText();
    }
}
