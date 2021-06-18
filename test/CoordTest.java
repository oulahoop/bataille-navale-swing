import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.ships.ICoord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Des cas de tests pour la classe Coord
 */


public class CoordTest {


    @Test
    public void testCoord1() throws Exception {
        ICoord c = new Coord("A5");
        assertEquals(1, c.getX());
        assertEquals('A', c.getAlphaX());
        assertEquals(5, c.getY());
    }

    @Test
    public void testCoord2() throws Exception {
        ICoord c = new Coord("E5");
        assertEquals(5, c.getX());
        assertEquals('E', c.getAlphaX());
        assertEquals(5, c.getY());
    }

    @Test
    public void testCoord3() throws Exception {
        ICoord c = new Coord("J5");
        assertEquals(10, c.getX());
        assertEquals('J', c.getAlphaX());
        assertEquals(5, c.getY());
    }

    @Test
    public void testCoord4() throws Exception {
        ICoord c = new Coord("E1");
        assertEquals(5, c.getX());
        assertEquals('E', c.getAlphaX());
        assertEquals(1, c.getY());
    }

    @Test
    public void testCoord5() throws Exception {
        ICoord c = new Coord("E10");
        assertEquals(5, c.getX());
        assertEquals('E', c.getAlphaX());
        assertEquals(10, c.getY());
    }


    @Test
    public void testCoordException() throws Exception {
        assertThrows(BadCoordException.class, () ->new Coord("15"));
    }


    @Test
    public void testCoordException2() throws Exception {
        try {
            new Coord("05");
            fail("Exception expected!!!");
        }
        catch(BadCoordException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCoordException3() throws Exception {
        assertThrows(BadCoordException.class, () ->new Coord("95"));
    }

    @Test
    public void testCoordException4() throws Exception {
        assertThrows(BadCoordException.class, () ->new Coord("K5"));
    }

    @Test
    public void testCoordException5() throws Exception {
        assertThrows(BadCoordException.class, () ->new Coord("E0"));
    }

    @Test
    public void testCoordException6() throws Exception {
        assertThrows(BadCoordException.class, () ->new Coord("E11"));
    }


    @Test
    public void testEgaliteCoords1()throws Exception {
        ICoord c = new Coord("E5");
        assertTrue(c.equals(c));
    }

    @Test
    public void testEgaliteCoords2()throws Exception {
        ICoord c = new Coord("E5");
        assertTrue(c.equals(new Coord("E5")));
    }

    @Test
    public void testNonEgaliteCoords1()throws Exception {
        ICoord c = new Coord("E5");
        assertFalse(c.equals(new Coord("A5")));
    }

    @Test
    public void testNonEgaliteCoords2()throws Exception {
        ICoord c = new Coord("E5");
        assertFalse(c.equals(new Coord("E1")));
    }


    @Test
    public void testComparaisonCoord_1() throws Exception {
        ICoord c1 = new Coord("E5");
        ICoord c2 = new Coord("I9");
        assertTrue(c1.compareTo(c2) < 0);
    }

    @Test
    public void testComparaisonCoord_2() throws Exception {
        ICoord c1 = new Coord("E5");
        ICoord c2 = new Coord("E9");
        assertTrue(c1.compareTo(c2) < 0);
    }

    @Test
    public void testComparaisonCoord_3() throws Exception {
        ICoord c1 = new Coord("E5");
        ICoord c2 = new Coord("I5");
        assertTrue(c1.compareTo(c2) < 0);
    }

    @Test
    public void testComparaisonCoord_4() throws Exception {
        ICoord c1 = new Coord("E5");
        ICoord c2 = new Coord("E3");
        assertTrue(c1.compareTo(c2) > 0);
    }

    @Test
    public void testEgalite_compare_Coords1()throws Exception {
        ICoord c = new Coord("E5");
        assertTrue(c.compareTo(c) == 0);
    }

    @Test
    public void testEgalite_compare_Coords2()throws Exception {
        ICoord c = new Coord("E5");
        assertTrue(c.compareTo(new Coord("E5")) == 0);
    }

    @Test
    public void testNonEgalite_compare_Coords1()throws Exception {
        ICoord c = new Coord("E5");
        assertFalse(c.compareTo(new Coord("A5")) == 0);
    }

    @Test
    public void testNonEgalite_compare_Coords2()throws Exception {
        ICoord c = new Coord("E5");
        assertFalse(c.compareTo(new Coord("E1")) == 0);
    }

    @Test
    public void testHashCode() throws  Exception {
        ICoord c = new Coord("E5");
        ICoord c2 = new Coord("E5");
        assertTrue(c.hashCode() == c2.hashCode());
    }

    @Test
    public void testHashCode2() throws  Exception {
        ICoord c = new Coord("E5");
        assertTrue(c.hashCode() == c.hashCode());
    }

    @Test
    public void testHashCodeNot() throws  Exception {
        ICoord c = new Coord("E5");
        ICoord c2 = new Coord("E1");
        assertFalse(c.hashCode() == c2.hashCode());
    }


    @Test
    public void testHashCodeNot2() throws  Exception {
        ICoord c = new Coord("E5");
        ICoord c2 = new Coord("F5");
        assertFalse(c.hashCode() == c2.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        ICoord c = new Coord("E5");
        assertEquals("E5", c.toString());
    }

    @Test
    public void testToStringNot() throws Exception {
        ICoord c = new Coord("E5");
        assertNotEquals("e5", c.toString());
    }


}