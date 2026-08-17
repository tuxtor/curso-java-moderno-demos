package com.vorozco.modernjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JEP110 {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts/1";

    public static void main(String[] args) {
        System.out.println(oldHttpClientDemo());
        System.out.println("Final");
        System.out.println("Hola mundo");
    }

    private static String oldHttpClientDemo() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            return response.toString();

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
