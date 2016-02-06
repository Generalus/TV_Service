package ru.thesn.tvs.etvsl;

import org.junit.Test;
import ru.thesn.tvs.etvsl.controller.MyController;
import ru.thesn.tvs.etvsl.exception.IncorrectDataException;
import java.util.Arrays;
import static org.junit.Assert.*;


public class GetParamsArrayTest {

    MyController controller = new MyController();

    @Test(expected = IncorrectDataException.class)
    public void test1() throws IncorrectDataException{
        String[] arr = {"12345", "23456", "3456"};
        controller.getParamsArray("2G", arr);
    }

    @Test(expected = IncorrectDataException.class)
    public void test2() throws IncorrectDataException{
        String[] arr = {"12345", "DFGHJ", "3456"};
        controller.getParamsArray("1", arr);
    }

    @Test(expected = IncorrectDataException.class)
    public void test3() throws IncorrectDataException{
        String[] arr = {"0.3"};
        controller.getParamsArray("11", arr);
    }

    @Test
    public void test4() throws IncorrectDataException{
        Integer[] ans = {2, 823, 736};
        String[] arr = {"823", "736"};
        assertEquals(Arrays.deepToString(ans), Arrays.deepToString(controller.getParamsArray("2", arr)));
    }
}
