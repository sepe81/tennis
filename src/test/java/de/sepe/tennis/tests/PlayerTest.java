package de.sepe.tennis.tests;

import junit.framework.TestCase;
import de.sepe.tennis.local.Player;

/**
 * tests the Player class.
 * 
 * @author Sebastian Peters
 * @since 12.02.2003
 */
public class PlayerTest extends TestCase {

    // the two players
    Player p1, p2;

    @Override
    protected void setUp() {
        p1 = new Player("Boris Becker");
        p2 = new Player("Michael Stich");
    }

    @Override
    protected void tearDown() {
        p1 = p2 = null;
    }

    public void testDefault() {
        assertNotNull(p1);
        assertNotNull(p2);
    }

    public void testSetter() {
        final String n = "Rothaar";
        p1.setName(n);
        assertEquals(p1.getName(), n);
        p2.setName("André Agasi");
        assertEquals(p2.getName(), "André Agasi");
    }
}
