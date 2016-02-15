package ru.thesn.tvs.cas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import ru.thesn.tvs.cas.enumeration.ResponseCode;
import ru.thesn.tvs.cas.model.CIMResponse;
import ru.thesn.tvs.cas.model.MainResponse;
import ru.thesn.tvs.cas.model.TVSResponse;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
@RequestMapping("/")
public class MyController {

    @RequestMapping(value = "cas", method = RequestMethod.GET)
    public @ResponseBody MainResponse getResponseInJSON(@RequestParam String login, @RequestParam String passwordHash){
        try {
            MainResponse response = new MainResponse(ResponseCode.OK.name());

            CIMResponse cimResponse = getCIMResponse(login, passwordHash);
            if (!(cimResponse.getCode().equals(ResponseCode.OK.name())))
                return new MainResponse(cimResponse.getCode(), cimResponse.getErrorMessage());

            TVSResponse tvsResponse = getTVSResponse(cimResponse.getAreaID().toString(), cimResponse.getProductsStringArray());
            if (!(tvsResponse.getCode().equals(ResponseCode.OK.name())))
                return new MainResponse(tvsResponse.getCode(), tvsResponse.getErrorMessage());

            response.setChannels(tvsResponse.getChannels());

            return response;
        }
        catch (FileNotFoundException | ConnectException e){
            return new MainResponse(ResponseCode.ERR.name(), "Вспомогательный сервер не отвечает!");
        }
        catch (Exception e){
            e.printStackTrace();
            return new MainResponse(ResponseCode.CRITICAL_ERROR.name(), "Критическая ошибка системы: " + e.toString());
        }
    }


    public CIMResponse getCIMResponse(String login, String passwordHash) throws Exception{
        String url = String.format("http://localhost:8050/rest/cim?login=%s&passwordHash=%s", login, passwordHash);

        String jsonString = connectAndReadJSONData(url);
        StringReader reader = new StringReader(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(reader, CIMResponse.class);
    }

    public TVSResponse getTVSResponse(String areaID, String[] packageIDs) throws Exception{
        StringBuilder url = new StringBuilder();
        url.append("http://localhost:8055/rest/tvsl?areaId=").append(areaID);

        for(String pID: packageIDs)
            url.append("&packageIds[]=").append(pID);

        String jsonString = connectAndReadJSONData(url.toString());
        StringReader reader = new StringReader(jsonString);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(reader, TVSResponse.class);
    }


    public String connectAndReadJSONData(String urlStr) throws IOException {
        System.out.println("Подключаюсь к " + urlStr);
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line);
        br.close();
        String result = new String(sb.toString().getBytes("Cp1251"), "UTF-8");
        System.out.println("Получены данные: \n" + result);
        return result;
    }
}
