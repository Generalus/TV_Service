package ru.thesn.tvs.cas.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class TestClient {

    public static final String MESSAGE = "Для успешного прохождения тестов необходимо"
            + " запустить все три модуля и БД должна содержать данные только из папки test_data...";


    public final static String FILE_PATH = System.getProperty("user.dir") + "\\Content Authorization Service\\src\\main\\java\\ru\\thesn\\tvs\\cas\\test\\tests.txt";


    public static void main(String[] args) throws Exception {
        System.out.println(MESSAGE);

        Path path = Paths.get(FILE_PATH);
        System.out.println(FILE_PATH);
        if (Files.notExists(path)) throw new FileNotFoundException("Файл не существует!");
        List<String> list = Files.readAllLines(path);
        if (list.size() % 2 != 0) throw new IOException("Файл поврежден и содержит нечетное число строк!");

        boolean result = true;
        for(int i = 0; i < list.size(); i+=2){
            boolean currentTest = list.get(i+1).equals(connectAndReadJSONData(list.get(i)));
            result &= currentTest;
            System.out.println("Test " + (i/2+1) + ": " + currentTest);
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
