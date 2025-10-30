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
        assertSame(Color.white, court.getBackground());
        court.setCourtType(Court.Type.GRASS);
        assertSame(Color.green, court.getBackground());
        court.setCourtType(Court.Type.HARD);
        assertSame(Color.red, court.getBackground());
        court.setCourtType(Court.Type.SAND);
        assertSame(Color.yellow, court.getBackground());
    }
}
