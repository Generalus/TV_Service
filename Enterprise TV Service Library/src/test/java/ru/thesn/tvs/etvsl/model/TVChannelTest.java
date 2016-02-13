package ru.thesn.tvs.etvsl.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TVChannelTest {

    @Test
    public void testEqualsMethod_shouldCompareEntitiesIDsOnly(){
        TVChannel c1 = new TVChannel();
        c1.setSourceID(2L);
        c1.setName("Cool");
        c1.setStatus("ACTIVE");
        c1.setContentID(234L);

        TVChannel c2 = new TVChannel();
        c2.setSourceID(2L);
        c2.setName("Bad");
        c2.setStatus("OFF");
        c2.setContentID(234L);

        assertEquals(c1, c2);
    }
}
