package opensudoku.game;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.moire.opensudoku.*;
import org.moire.opensudoku.game.Cell;
import org.moire.opensudoku.game.CellGroup;
import org.moire.opensudoku.game.CellNote;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.lang.IllegalArgumentException;
import java.util.StringTokenizer;
/**
 * Created by cyprian on 2/12/2018.
 */
public class CellTest {

    Cell myCell;

    @Before
    public void init(){
        myCell = new Cell();
    }

    @Test
    public void givenCell_WhenInstantiatingObject_ThenObjectCreated(){
        assertNotNull(myCell);
    }

    @Test
    public void getRowIndex() throws Exception {
        assertEquals(1, myCell.getRowIndex(), 0);
    }

    @Test
    public void getColumnIndex() throws Exception {
        assertEquals(1, myCell.getColumnIndex(), 0);
    }

    public void testInitCollection() throws Exception {
    }

    @Test
    public void getSector() throws Exception {
        CellGroup testSector = myCell.getSector();
        assertEquals(testSector, myCell.getSector());
    }

    @Test
    public void getRow() throws Exception {
        CellGroup testRow = myCell.getRow();
        assertEquals(testRow, myCell.getRow());
    }

    @Test
    public void getColumn() throws Exception {
        CellGroup testCol = myCell.getColumn();
        assertEquals(testCol, myCell.getColumn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setValue() throws Exception {
        myCell.setValue(10);
        assertEquals("Checking setValue method", 9, myCell.getValue());
    }

    @Test
    public void getValue() throws Exception {
        assertEquals(1, myCell.getValue(), 0);
    }

    @Test
    public void getNote() throws Exception {
        CellNote testCellNote = myCell.getNote();
        assertEquals(testCellNote, myCell.getNote());
    }

    @Test
    public void setNote() throws Exception {
        CellNote testCellNote = new CellNote();
        myCell.setNote(testCellNote);
        assertEquals(testCellNote, myCell.getNote());
    }

    public void testIsEditable() throws Exception {

        //Has no side effect
    }

    @Test
    public void testSetEditable() throws Exception {
        myCell.setEditable(true);
        assertTrue(myCell.isEditable());
    }

    @Test
    public void testSetValid() throws Exception {
        myCell.setValid(false);
        assertFalse(myCell.isValid());
    }

    public void testIsValid() throws Exception {

        //Has no side effect
    }

    @Test
    /*public void testDeserialize() throws Exception {
        String str = "1 2 3 4 5 6";
        StringTokenizer st = new StringTokenizer(str);
        Cell testCell = Cell.deserialize(str);
        assertEquals(testCell, Cell.deserialize(str));
    }*/


    public void testSerialize() throws Exception {
    }

    @After
    public void destroy(){
        myCell = null;
    }

}