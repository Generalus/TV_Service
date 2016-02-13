package ru.thesn.tvs.etvsl.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.exception.IncorrectDataException;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.service.LineupService;
import ru.thesn.tvs.etvsl.service.TVPackageService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyControllerTest {
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


    @Test(expected = IncorrectDataException.class)
    public void testGetParamsArray_shouldGenerateExceptionIfAreaIdIsString() throws IncorrectDataException{
        String[] arr = {"12345", "23456", "3456"};
        controller.getParamsArray("2G", arr);
    }

    @Test(expected = IncorrectDataException.class)
    public void testGetParamsArray_shouldGenerateExceptionIfInputPackageIdIsString() throws IncorrectDataException{
        String[] arr = {"12345", "DFGHJ", "3456"};
        controller.getParamsArray("1", arr);
    }

    @Test(expected = IncorrectDataException.class)
    public void testGetParamsArray_shouldGenerateExceptionIfInputPackageIdIsDoubleNumber() throws IncorrectDataException{
        String[] arr = {"0.3"};
        controller.getParamsArray("11", arr);
    }

    @Test
    public void testGetParamsArray_shouldCreateParamsArrayCorrectly() throws IncorrectDataException{
        Integer[] ans = {2, 823, 736};
        String[] arr = {"823", "736"};
        assertEquals(Arrays.deepToString(ans), Arrays.deepToString(controller.getParamsArray("2", arr)));
    }

    @Test
    public void testFindChannels_shouldCheckChannelsListSize() throws EntityNotFoundException{
        List<TVChannel> list = controller.findChannels(params);
        verify(lineupService, times(1)).findById(params[0]);
        for(int i = 1; i < params.length; i++) {
            verify(tvPackageService, times(1)).findById(params[i]);
        }
        assertEquals(3, list.size());
    }

    @Test
    public void testFindChannels_shouldCheckChannelsList() throws EntityNotFoundException{
        List<TVChannel> list = controller.findChannels(params);
        verify(lineupService, times(1)).findById(params[0]);
        for(int i = 1; i < params.length; i++) {
            verify(tvPackageService, times(1)).findById(params[i]);
        }
        for(int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getSourceID(), ans.get(i).getSourceID());
        }
    }





}
