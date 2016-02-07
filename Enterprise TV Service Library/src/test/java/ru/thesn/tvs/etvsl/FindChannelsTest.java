package ru.thesn.tvs.etvsl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.thesn.tvs.etvsl.controller.MyController;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.service.LineupService;
import ru.thesn.tvs.etvsl.service.TVPackageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FindChannelsTest {
    @Mock
    private LineupService lineupService;

    @Mock
    private TVPackageService tvPackageService;

    @InjectMocks
    private MyController controller;

    Integer[] params = {1, 1001, 1003};
    TVChannel[] ch;
    List<TVChannel> ans;

    @Before
    public void init(){
        ch = new TVChannel[5];

        for(int i = 0; i < ch.length; i++) {
            ch[i] = new TVChannel();
            ch[i].setSourceID(10011L + i*10);
            ch[i].setStatus("ACTIVE");
        }

        Lineup lineup = new Lineup();
        lineup.setAreaID(1L);
        lineup.setName("New York");
        Set<TVChannel> set = new HashSet<>();
        set.add(ch[0]);
        set.add(ch[1]);
        set.add(ch[3]);
        set.add(ch[4]);
        lineup.setChannels(set);

        TVPackage tvp1 = new TVPackage();
        tvp1.setOfferingID(1001L);
        tvp1.setStatus("ACTIVE");
        set = new HashSet<>();
        set.add(ch[0]);
        set.add(ch[1]);
        tvp1.setChannels(set);

        TVPackage tvp2 = new TVPackage();
        tvp2.setOfferingID(1003L);
        tvp2.setStatus("ACTIVE");
        set = new HashSet<>();
        set.add(ch[0]);
        set.add(ch[2]);
        set.add(ch[4]);
        tvp2.setChannels(set);

        when(lineupService.findById(params[0])).thenReturn(lineup);
        when(tvPackageService.findById(params[1])).thenReturn(tvp1);
        when(tvPackageService.findById(params[2])).thenReturn(tvp2);

        ans = new ArrayList<>(); // пересечение множеств Lineup и объединения (tvp1 + tvp2)
        ans.add(ch[0]);
        ans.add(ch[1]);
        ans.add(ch[4]);
    }

    @Test
    public void test1() throws Exception{
        List<TVChannel> list = controller.findChannels(params);
        assertEquals(3, list.size());
    }

    @Test
    public void test2() throws Exception{
        List<TVChannel> list = controller.findChannels(params);
        for(int i = 0; i < list.size(); i++)
            assertEquals(list.get(i).getSourceID(), ans.get(i).getSourceID());
    }
}