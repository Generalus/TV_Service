package ru.thesn.tvs.etvsl.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineupTest {
    @Test
    public void testEqualsMethod_shouldCompareEntitiesIDsOnly(){
        Lineup l1 = new Lineup();
        l1.setAreaID(1L);
        l1.setName("New York");

        Lineup l2 = new Lineup();
        l2.setAreaID(1L);
        l2.setName("London");

        assertEquals(l1, l2);
    }
}
