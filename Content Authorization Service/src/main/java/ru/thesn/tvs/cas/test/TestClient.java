package ru.thesn.tvs.cas.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestClient {

    public static final String MESSAGE = "Для успешного прохождения тестов необходимо"
            + " запустить все три модуля и БД должна содержать данные только из папки test_data...";

    public final static Map<String, String> TESTS;

    static{
        TESTS = new HashMap<>(); // key = url, value = expected result

        TESTS.put("http://localhost:8080/rest/cas?login=admin&passwordHash=dg12a",
                "{\"code\":\"OK\",\"errorMessage\":null,\"channels\":" +
                "[{\"sourceID\":10011,\"name\":\"Animal Planet\",\"contentID\":9001}," +
                "{\"sourceID\":10012,\"name\":\"Animal Planet HD\",\"contentID\":9001}," +
                "{\"sourceID\":10031,\"name\":\"HBO HD\",\"contentID\":9003}," +
                "{\"sourceID\":10041,\"name\":\"HBO East HD\",\"contentID\":9004}]}");

        TESTS.put("http://localhost:8080/rest/cas?login=ololosha&passwordHash=Gdg4f",
                "{\"code\":\"OK\",\"errorMessage\":null,\"channels\":[" +
                "{\"sourceID\":10031,\"name\":\"HBO HD\",\"contentID\":9003}," +
                "{\"sourceID\":10041,\"name\":\"HBO East HD\",\"contentID\":9004}]}");

        TESTS.put("http://localhost:8080/rest/cas?login=ghost&passwordHash=dfgdfg",
                "{\"code\":\"WARN\",\"errorMessage\":\"Уведомление: Заданный аккаунт не существует!\"," +
                "\"channels\":null}");

        TESTS.put("http://localhost:8080/rest/cas?login=admin&passwordHash=wrong_hash",
                "{\"code\":\"ERR\",\"errorMessage\":\"Ошибка доступа: Пароль неверен!\",\"channels\":null}");

        TESTS.put("http://localhost:8080/rest/cas?login=volk2005&passwordHash=gc54d",
                "{\"code\":\"OK\",\"errorMessage\":null,\"channels\":[" +
                "{\"sourceID\":10011,\"name\":\"Animal Planet\",\"contentID\":9001}]}");

    }


    public static void main(String[] args) throws IOException {
        System.out.println(MESSAGE);
        int i = 1;
        boolean result = true;
        for(Map.Entry<String, String> entry: TESTS.entrySet()){
            boolean currentTest = entry.getValue().equals(connectAndReadJSONData(entry.getKey()));
            result &= currentTest;
            System.out.println("Test " + i + ": " + currentTest);
            i++;
        }
        System.out.println("\n === " + (result ? "ПРОЙДЕНЫ ВСЕ ТЕСТЫ!" : "ЕСТЬ ОШИБКИ!") + " ===");
    }


    public static String connectAndReadJSONData(String urlStr) throws IOException {
        System.out.println("Подключаюсь к " + urlStr);
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line);
        br.close();
        System.out.println("Получены данные: \n" + sb.toString());
        return sb.toString();
    }
}
