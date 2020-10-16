package info.firozansari.architecture_component.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
public class DateUtilsTest {

    @Test
    public void compereDateToConvertedText() throws Exception {
        assertEquals(DateUtils.covertDateToText("2020-04-17T12:13:44.575Z"), "6 Months Ago");
    }

}