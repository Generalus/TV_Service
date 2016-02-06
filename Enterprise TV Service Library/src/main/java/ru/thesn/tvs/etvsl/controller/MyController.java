package ru.thesn.tvs.etvsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.thesn.tvs.etvsl.model.Lineup;
import ru.thesn.tvs.etvsl.model.Response;
import ru.thesn.tvs.etvsl.model.TVChannel;
import ru.thesn.tvs.etvsl.model.TVPackage;
import ru.thesn.tvs.etvsl.service.LineupService;
import ru.thesn.tvs.etvsl.service.TVChannelService;
import ru.thesn.tvs.etvsl.service.TVPackageService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    TVChannelService channelService;

    @Autowired
    LineupService lineupService;

    @Autowired
    TVPackageService tvPackageService;

    @RequestMapping(value = "tvs", method = RequestMethod.GET)
    public @ResponseBody Response getResponseInJSON(@RequestParam String areaId, @RequestParam(value="packageIds[]") String[] packageIds){
        try {
            Integer[] params = new Integer[packageIds.length + 1];

            try {
                params[0] = Integer.parseInt(areaId);
            } catch (NumberFormatException e) {
                return new Response("ERR", "Параметр areaId должен быть типа Integer!");
            }

            for (int i = 0; i < packageIds.length; i++) {
                try {
                    params[i + 1] = Integer.parseInt(packageIds[i]);
                } catch (NumberFormatException e) {
                    return new Response("ERR", "Параметры packageIds должны быть типа Integer!");
                }
            }

            Lineup lineup = lineupService.findById(params[0]);
            if (lineup == null) return new Response("ERR", "Задан несуществующий Lineup: " + params[0]);
            Set<TVChannel> set1 = lineup.getChannels();

            Set<TVChannel> set2 = new HashSet<>();
            for (int i = 1; i < params.length; i++) {
                TVPackage tvPackage = tvPackageService.findById(params[i]);
                if (tvPackage == null) return new Response("ERR", "Задан несуществующий TVPackage: " + params[i]);
                set2.addAll(tvPackage.getChannels());
            }

            Set<TVChannel> channels = new HashSet<>();
            for (TVChannel channel : set2)
                if (set1.contains(channel) && "ACTIVE".equals(channel.getStatus())) channels.add(channel);

            if (channels.size() == 0)
                return new Response("NOT_FOUND", "По данному запросу активных каналов не нашлось!");

            Response response = new Response("OK");
            response.setChannels(channels);
            return response;

        }catch (Exception e){
            e.printStackTrace();
            return new Response("CRITICAL_ERROR", "Критическая ошибка системы: " + e.toString());
        }
    }
}
