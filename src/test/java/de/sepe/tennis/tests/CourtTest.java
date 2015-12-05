package de.sepe.tennis.tests;

import java.awt.Color;

import junit.framework.TestCase;
import de.sepe.tennis.local.Court;

/**
 * tests the Court class.
 * 
 * @author Sebastian Peters
 * @since 21.02.2003
 */
public class CourtTest extends TestCase {

    private Court court;

    @Override
    protected void setUp() {
        court = new Court();
    }

    @Override
    protected void tearDown() {
        court = null;
    }

    public void testDefault() {
        assertNotNull(court);
    }

    public void testCourtType() {
        assertTrue(court.getBackground() == Color.white);
        court.setCourtType(Court.Type.GRASS);
        assertTrue(court.getBackground() == Color.green);
        court.setCourtType(Court.Type.HARD);
        assertTrue(court.getBackground() == Color.red);
        court.setCourtType(Court.Type.SAND);
        assertTrue(court.getBackground() == Color.yellow);
    }
}
