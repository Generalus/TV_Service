package ru.thesn.tvs.etvsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    LineupService lineupService;

    @Autowired
    TVPackageService tvPackageService;

    @RequestMapping(value = "tvsl", method = RequestMethod.GET)
    public @ResponseBody Response getResponseInJSON(@RequestParam String areaId, @RequestParam(value="packageIds[]") String[] packageIds){
        try {
            Response response = new Response(ResponseCode.OK.name());
            response.setChannels(findChannels(getParamsArray(areaId, packageIds)));
            return response;
        }
        catch (EntityNotFoundException e){
            return new Response(ResponseCode.WARN.name(), "Уведомление: " + e.getMessage());
        }
        catch (IncorrectDataException e){
            return new Response(ResponseCode.ERR.name(), "Ошибка системы: " + e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return new Response(ResponseCode.CRITICAL_ERROR.name(), "Критическая ошибка системы: " + e.toString());
        }
    }

    public List<TVChannel> findChannels(Integer[] params) throws EntityNotFoundException {
        Lineup lineup = lineupService.findById(params[0]);
        if (lineup == null) throw new EntityNotFoundException("Задан несуществующий Lineup: " + params[0]);
        Set<TVChannel> set1 = lineup.getChannels();

        Set<TVChannel> set2 = new HashSet<>();
        for (int i = 1; i < params.length; i++) {
            TVPackage tvPackage = tvPackageService.findById(params[i]);
            if (tvPackage == null || !StatusCode.ACTIVE.name().equals(tvPackage.getStatus())) throw new EntityNotFoundException("Задан несуществующий/неактивный TVPackage: " + params[i]);
            set2.addAll(tvPackage.getChannels());
        }

        Set<TVChannel> channels = new HashSet<>();
        for (TVChannel channel : set2)
            if (set1.contains(channel) && StatusCode.ACTIVE.name().equals(channel.getStatus())) channels.add(channel);

        if (channels.size() == 0)
            throw new EntityNotFoundException("По данному запросу активных каналов не нашлось!");
        // в данном случае, отсутствие каналов - скорее недочет системы, чем обычная ситуация

        List<TVChannel> result = new ArrayList<>();
        result.addAll(channels);
        Collections.sort(result, new Comparator<TVChannel>() {
            @Override
            public int compare(TVChannel c1, TVChannel c2) {
                return (int)(c1.getSourceID() - c2.getSourceID()); // сортировка в порядке возрастания ID
            }
        });

        return result;
    }


    public Integer[] getParamsArray(String s, String[] arr) throws IncorrectDataException{
        Integer[] result = new Integer[arr.length + 1];

        try {
            result[0] = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IncorrectDataException("Параметр areaId должен быть типа Integer!");
        }

        for (int i = 0; i < arr.length; i++) {
            try {
                result[i + 1] = Integer.parseInt(arr[i]);
            } catch (NumberFormatException e) {
                throw new IncorrectDataException("Параметры packageIds должны быть типа Integer!");
            }
        }
        return result;
    }

}
