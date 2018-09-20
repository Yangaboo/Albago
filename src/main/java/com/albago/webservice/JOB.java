package com.albago.webservice;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class JOB {
    private String url;
    private String pwd;
    private String startX;
    private String startY;

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStartX(String startX) {
        this.startX = startX;
    }

    public void setStartY(String startY) {
        this.startY = startY;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUrl() {
        return url;
    }

    public String getStartX() {
        return startX;
    }

    public String getStartY() {
        return startY;
    }

    public ArrayList convertAddress(String address) throws IOException {
        String apiKey = "53cc8319-52df-4dfa-ba76-fbcc52ccdc99";
        String Accept = "application/json";

        String location = URLEncoder.encode(address, "UTF-8");
        String apiURL = "https://api2.sktelecom.com/tmap/geo/convertAddress?version=1&searchTypCd=NtoO&reqAdd=" + location + "&appKey=" + apiKey;
        java.net.URL url = new java.net.URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", Accept);
        int responseCode = con.getResponseCode();
        BufferedReader br;

        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        String response = null;
        ArrayList geoLocation = new ArrayList();
        if ((inputLine = br.readLine()) != null) {
            response = inputLine;

            geoLocation.add(response.substring(371, 382));
            geoLocation.add(response.substring(394, 406));
        }

        return geoLocation;
    }

    public String findDistance(String startX, String startY, String endX, String endY) throws IOException {
        String apiKey = "53cc8319-52df-4dfa-ba76-fbcc52ccdc99";
        String Accept = "application/json";

        String apiUrl = "https://api2.sktelecom.com/tmap/routes?version=1&startX=" + startX + "&startY=" + startY + "&endX=" + endX + "&endY=" + endY + "&totalValue=2";
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", Accept);
        con.setRequestProperty("appKey", apiKey);
        int responseCode = con.getResponseCode();
        BufferedReader br;

        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        String response = null;
        String totalDistance = null;
        if ((inputLine = br.readLine()) != null) {
            response = inputLine;
            totalDistance = response.substring(110, 116).replaceAll("[^0-9]", "");
        }

        return totalDistance;
    }

    public HashMap<String, String> Crawling(JOB url) throws IOException {
        HashMap<String, String> res = new HashMap<String, String>();
        String uri;
        uri = url.getUrl();
        Connection.Response response = Jsoup.connect(uri)
                .method(Connection.Method.GET)
                .execute();
        Document albaDocument = response.parse();
        Element address = albaDocument.select("li[class=address divide]").select("strong").first();
        Element title = albaDocument.select("p[class=detailTitle]").first();
        Element workTime = albaDocument.select("li[class=worktimecd]").first();
        Element period = albaDocument.select("li[class=workperiodcd]").select("a").first();
        Element pay = albaDocument.select("p[class=pay]").first();
        Element workDate = albaDocument.select("li[class=workweekcd]").select("a").first();
        Element age = albaDocument.select("li[class=agetemp]").first();

        String addressValue = address.text();
        String titleValue = title.text();
        String workTimeValue = workTime.text();
        String periodValue = period.text();
        String payValue;
        String workDateValue = workDate.text();
        String ageValue = age.text();


        if (pay == null) {
            payValue = "추후 협의";
        } else {
            payValue = pay.text();
        }

        res.put("address", addressValue);
        res.put("name", titleValue);
        res.put("workTime", workTimeValue);
        res.put("period", periodValue);
        res.put("hourlyWage", payValue);
        res.put("days", workDateValue);
        res.put("age", ageValue);
        return res;
    }
}
