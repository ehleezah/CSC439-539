package opensudoku.game;

import org.junit.Before;
import org.junit.Test;
import org.moire.opensudoku.game.CellNote;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Eliza Karki on 4/9/2018.
 */

public class CellNoteTest {
    CellNote myCell;

    @Before
    public void init() { myCell = new CellNote(); }

    @Test
    public void givenCellNote_WhenInstantiatingObject_ThenObjectCreated() {
        assertNotNull(myCell);
    }


    @Test
    public void serializeTest() {
        String testCellNote = myCell.serialize();
        assertEquals(testCellNote, myCell.serialize());
    }
//    @Test
    /**
     * Adds number to the cell's note (if not present already).
     *
     * @param //number
     * @return
     */



    @Test
    public void getNotedNumbers() {
        List<Integer> testCellNote = myCell.getNotedNumbers();
        assertNotNull(testCellNote);
    }

    @Test
    public void toggleNumberTest() throws Exception {
        CellNote testCellNote = myCell.toggleNumber(8);
        assertNotNull(testCellNote);
    }

    @Test
    public void addNumberTest() throws Exception {
        CellNote testCellNote = myCell.addNumber(8);
        assertNotNull(testCellNote);
    }

    @Test
    public void removeNumberTest() throws Exception {
        CellNote testCellNote = myCell.removeNumber(8);
        assertNotNull(testCellNote);
    }

    @Test
    public void clearTest() {
        CellNote testCellNote = myCell.clear();
        assertNotNull(testCellNote);
    }

    @Test
    public void isEmptyTest() {
        assertNotNull(myCell.clear());
    }


}
