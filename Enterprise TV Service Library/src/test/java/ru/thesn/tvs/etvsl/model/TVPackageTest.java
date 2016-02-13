package ru.thesn.tvs.etvsl.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TVPackageTest {
    @Test
    public void testEqualsMethod_shouldCompareEntitiesIDsOnly(){
        TVPackage p1 = new TVPackage();
        p1.setOfferingID(3L);
        p1.setName("Ultimate");
        p1.setStatus("ACTIVE");

        TVPackage p2 = new TVPackage();
        p2.setOfferingID(3L);
        p2.setName("Pro");
        p2.setStatus("OFF");

        assertEquals(p1, p2);
    }
}
