package opensudoku.game;

import org.junit.Test;
import org.moire.opensudoku.game.Cell;
import org.moire.opensudoku.game.CellGroup;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Apekshya on 3/25/2018.
 */
public class CellGroupTest {

    CellGroup cellGroup = new CellGroup();
    Cell[] cell = new Cell[9];


    public CellGroupTest()

    {
        for (int i = 0; i < 9; i++) {
            cell[i] = new Cell(i);
        }

        for (int i = 0; i < 9; i++) {
            cellGroup.addCell(cell[i]);
        }
    }



    // private Cell[] mCellsTest = new Cell[9];


    @Test
    public void addCellTest() throws Exception {

        assertNotNull(cellGroup.mCells);
        System.out.println(cellGroup.mCells[1].getValue());

    }

    @Test
    public void validateTest() {
        boolean sut = cellGroup.validate();
        assertFalse(sut);
    }

    @Test
    public void containsTest() {

        boolean sut = cellGroup.contains(1);
        boolean sut1 = cellGroup.contains(9);
        assertTrue(sut);
        assertFalse(sut1);
    }
}


