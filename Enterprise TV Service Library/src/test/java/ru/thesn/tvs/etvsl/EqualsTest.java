package ru.thesn.tvs.etvsl;

import org.junit.Test;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;

import static org.junit.Assert.*;

public class EqualsTest {
    @Test
    public void testLineupEquals(){
        Lineup l1 = new Lineup();
        l1.setAreaID(1L);
        l1.setName("New York");

        Lineup l2 = new Lineup();
        l2.setAreaID(1L);
        l2.setName("London");

        assertEquals(l1, l2);
    }

    @Test
    public void testChannelEquals(){
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

    @Test
    public void testPackageEquals(){
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
