package ru.thesn.tvs.etvsl.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.thesn.tvs.etvsl.enumeration.ResponseCode;
import ru.thesn.tvs.etvsl.enumeration.StatusCode;
import ru.thesn.tvs.etvsl.exception.EntityNotFoundException;
import ru.thesn.tvs.etvsl.exception.IncorrectDataException;
import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.Response;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.service.LineupService;
import ru.thesn.tvs.etvsl.service.TVPackageService;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
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
    Integer[] params2 = {2, 1006, 1008};
    Integer[] params3 = {3, 1007};
    Integer[] params4 = {4, 1017};

    TVChannel[] ch;
    List<TVChannel> ans;

    @Before
    public void init(){

        // =========== 1 набор данных =================

        ch = new TVChannel[5];

        for(int i = 0; i < ch.length; i++) {
            ch[i] = new TVChannel();
            ch[i].setSourceID(10011L + i*10);
            ch[i].setStatus(StatusCode.ACTIVE.name());
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
        tvp1.setStatus(StatusCode.ACTIVE.name());
        set = new HashSet<>();
        set.add(ch[0]);
        set.add(ch[1]);
        tvp1.setChannels(set);

        TVPackage tvp2 = new TVPackage();
        tvp2.setOfferingID(1003L);
        tvp2.setStatus(StatusCode.ACTIVE.name());
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


        // ======== 2 набор данных ================

        when(lineupService.findById(params2[0])).thenReturn(null);

        // ======== 3 набор данных ================

        Lineup lineup2 = new Lineup();
        lineup2.setAreaID(3L);
        lineup2.setName("New York");
        Set<TVChannel> set2 = new HashSet<>();
        lineup2.setChannels(set2);

        TVPackage tvp = new TVPackage();
        tvp.setOfferingID(1007L);
        tvp.setStatus(StatusCode.ACTIVE.name());
        set2 = new HashSet<>();
        set2.add(ch[0]);
        set2.add(ch[1]);
        tvp.setChannels(set2);

        when(lineupService.findById(params3[0])).thenReturn(lineup2);
        when(tvPackageService.findById(params3[1])).thenReturn(null);


        // ======== 4 набор данных ================

        Lineup lineup3 = new Lineup();
        lineup3.setAreaID(4L);
        lineup3.setName("New York");
        Set<TVChannel> set3 = new HashSet<>();
        lineup3.setChannels(set3);

        TVPackage tvp4 = new TVPackage();
        tvp4.setOfferingID(1017L);
        tvp4.setStatus(StatusCode.NOT_ACTIVE.name());
        set3 = new HashSet<>();
        set3.add(ch[0]);
        set3.add(ch[1]);
        tvp4.setChannels(set3);

        when(lineupService.findById(params4[0])).thenReturn(lineup3);
        when(tvPackageService.findById(params4[1])).thenReturn(tvp4);

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

    @Test(expected = EntityNotFoundException.class)
    public void testFindChannels_shouldThrowEntityNotFoundExceptionWhenChannelIsNULL() throws EntityNotFoundException{
        controller.findChannels(params2);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindChannels_shouldThrowEntityNotFoundExceptionWhenTVPackageIsNULL() throws EntityNotFoundException{
        controller.findChannels(params3);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindChannels_shouldThrowEntityNotFoundExceptionWhenTVPackageIsNotActive() throws EntityNotFoundException{
        controller.findChannels(params4);
    }

    @Test
    public void testGetResponseInJSON_shouldReturnOKResponseWhenInputDataIsCorrect(){
        String[] input = {"1001", "1003"};
        Response response = controller.getResponseInJSON("1", input);
        verify(lineupService, times(1)).findById(params[0]);
        for(int i = 1; i < params.length; i++) {
            verify(tvPackageService, times(1)).findById(params[i]);
        }
        assertArrayEquals(ResponseCode.OK.name().getBytes(), response.getCode().getBytes());
        for(int i = 0; i < response.getChannels().size(); i++) {
            assertEquals(response.getChannels().get(i).getSourceID(), ans.get(i).getSourceID());
        }
    }

    @Test
    public void testGetResponseInJSON_shouldReturnWarnResponseWhenLineupIsNULL(){
        String[] input = {"1006", "1008"};
        Response response = controller.getResponseInJSON("2", input);
        verify(lineupService, times(1)).findById(params2[0]);
        assertArrayEquals(ResponseCode.WARN.name().getBytes(), response.getCode().getBytes());
    }

    @Test
    public void testGetResponseInJSON_shouldReturnErrResponseWhenAreaIDIsNotNumber(){
        String[] input = {"1006", "1008"};
        Response response = controller.getResponseInJSON("not_integer", input);
        assertArrayEquals(ResponseCode.ERR.name().getBytes(), response.getCode().getBytes());
    }

    @Test
    public void testGetResponseInJSON_shouldReturnErrResponseWhenPackageIdsAreNotNumbers(){
        String[] input = {"not_integer", "1234"};
        Response response = controller.getResponseInJSON("1", input);
        assertArrayEquals(ResponseCode.ERR.name().getBytes(), response.getCode().getBytes());
    }

    @Test
    public void testGetResponseInJSON_shouldReturnWarnResponseWhenTVPackageIsNULL(){
        String[] input = {"1007"};
        Response response = controller.getResponseInJSON("3", input);
        assertArrayEquals(ResponseCode.WARN.name().getBytes(), response.getCode().getBytes());
    }

    @Test
    public void testGetResponseInJSON_shouldReturnWarnResponseWhenTVPackageIsNotActive(){
        String[] input = {"1017"};
        Response response = controller.getResponseInJSON("4", input);
        assertArrayEquals(ResponseCode.WARN.name().getBytes(), response.getCode().getBytes());
    }
}
