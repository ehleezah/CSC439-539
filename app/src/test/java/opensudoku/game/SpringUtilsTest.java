package opensudoku.game;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.moire.opensudoku.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class SpringUtilsTest {

    StringUtils sut;

    @Before
    public void init(){
        sut = new StringUtils();
    }

    @Test
    public void givenStringUtils_WhenInstantiatingObject_ThenObjectCreated(){
        assertNotNull(sut);
    }

    @Test
    public void joinTest(){
       List<String> testString = new ArrayList<String>();
       testString.add("one"); testString.add("two"); testString.add("three");
       String result = StringUtils.join(testString, ",");
       assertEquals("one,two,three", result);
    }
}
