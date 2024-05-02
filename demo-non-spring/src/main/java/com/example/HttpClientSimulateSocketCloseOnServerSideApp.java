package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HttpClientSimulateSocketCloseOnServerSideApp {

    public static void main(String[] args) {
        HttpClientSimulateSocketCloseOnServerSideApp httpClientSimulateSocketCloseOnServerSideApp = new HttpClientSimulateSocketCloseOnServerSideApp();
        httpClientSimulateSocketCloseOnServerSideApp.callHttpPost();
    }

    private void callHttpPost() {

        try {

            URL url = new URL("http://localhost:8080/api/validate");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setDoOutput(true);

            String json = """
                    {
                        "clientId": 1234,
                        "routeId" : 4567,
                        "clientConnectionId" :7889,
                        "connectionType": "HTTP",
                        "source": {
                            "ton": 90,
                            "npi": 91,
                            "address": "address value"
                        },
                        "destinations": [
                            {
                                "messageId": 4321,
                                "destination": {
                                "ton": 90,
                                "npi": 91,
                                "address": "address value"
                                }
                            }
                        ]
                    }
            """;

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            //Thread.sleep(5000);

            getResponseWithReadCharAndCloseConnectionDuringRead(http);
            //getResponseWithReadChar(http);
            //getResponseWithReadLine(http);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getResponseWithReadCharAndCloseConnectionDuringRead(HttpURLConnection http) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));

        StringBuilder sb = new StringBuilder();
        int charInt;
        while ((charInt = reader.read()) != -1) {
            char ch = (char) charInt;
            //System.out.println(" readChar : " + ch);
            sb.append(ch);

            System.out.println("Closing connection");
            http.disconnect();
            //reader.close();
            break;
        }
        String getResponseString = sb.toString();

        System.out.println(getResponseString);
    }


    private static void getResponseWithReadChar(HttpURLConnection http) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));

        StringBuilder sb = new StringBuilder();
        int charInt;
        while ((charInt = reader.read()) != -1) {
            char ch = (char) charInt;
            //System.out.println(" readChar : " + ch);
            sb.append(ch);
        }
        String getResponseString = sb.toString();

        System.out.println(getResponseString);
    }

    private static void getResponseWithReadLine(HttpURLConnection http) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(" line : " + line);
            sb.append(line);
        }
        String getResponseString = sb.toString();

        System.out.println(getResponseString);
    }


}
