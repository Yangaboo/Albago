package com.albago.webservice;

import org.json.JSONArray;
import org.json.JSONObject;
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

    public ArrayList convertAddress(String address) throws Exception {
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
        StringBuffer response = new StringBuffer();
        ArrayList geoLocation = new ArrayList();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        JSONObject myResponse = new JSONObject(response.toString());
        JSONObject convertAdd = myResponse.getJSONObject("ConvertAdd");

        geoLocation.add(convertAdd.getString("oldLat"));
        geoLocation.add(convertAdd.getString("oldLon"));

        return geoLocation;
    }

    public String findDistance(String startX, String startY, String endX, String endY) throws Exception {
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
        StringBuffer response = new StringBuffer();
        String totalDistance = null;
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray features = new JSONArray(myResponse.getJSONArray("features").toString());
        JSONObject tmp = new JSONObject(features.getJSONObject(0).toString());
        JSONObject properties = new JSONObject(tmp.getJSONObject("properties").toString());

        totalDistance = properties.getString("totalDistance");

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
        Element workDate = albaDocument.select("li[class=workweekcd]").first();
        Element age = albaDocument.select("li[class=agetemp]").first();
        Element sex = albaDocument.select("li[class=sex]").first();
        Element grade = albaDocument.select("li[class=lastSchool]").first();

        String addressValue = address.text();
        String titleValue = title.text();
        String workTimeValue = workTime.text().replaceAll("[^0-9&~]", "");
        String[] workTimeValues = workTimeValue.split("~");
        String periodValue = period.text().replace("1년", "12개월");
        String payValue;
        String workDateValue = workDate.text().substring(5);
        ArrayList workDateValues = new ArrayList();
        String ageValue = age.text();
        int workingTime = Integer.parseInt(workTimeValues[1].substring(0, 2)) - Integer.parseInt(workTimeValues[0].substring(0, 2));
        String sexValue = sex.text().substring(3);
        String gradeValue = grade.text();

        if (ageValue.contains("연령무관") && gradeValue.contains("학력무관")) {
            res.put("isTeen", String.valueOf(true));
        } else if (ageValue.contains("미성년자 불가")) {
            res.put("isTeen", String.valueOf(false));
        } else if (ageValue.contains("연령무관") && gradeValue.contains("고졸이상")) {
            res.put("isTeen", String.valueOf(false));
        }

        String workDateNew = workDateValue.replaceAll("(요일)", "").replaceAll("[^월화수목금토일]", "");

        System.out.println(workDateNew);

        if (workDateNew.contains("월")) {
            workDateValues.add("\"" + "월" + "\"");
        }

        if (workDateNew.contains("화")) {
            workDateValues.add("\"" + "화" + "\"");
        }

        if (workDateNew.contains("수")) {
            workDateValues.add("\"" + "수" + "\"");
        }

        if (workDateNew.contains("목")) {
            workDateValues.add("\"" + "목" + "\"");
        }

        if (workDateNew.contains("금")) {
            workDateValues.add("\"" + "금" + "\"");
        }

        if (workDateNew.contains("토")) {
            workDateValues.add("\"" + "토" + "\"");
        }

        if (workDateNew.contains("일")) {
            workDateValues.add("\"" + "일" + "\"");
        }

        if (pay == null) {
            payValue = "추후 협의";
            res.put("hourlyWage", payValue);
        } else {
            payValue = pay.text().replaceAll("[^0-9]", "");
            if (payValue.length() >= 7) {
                if (workingTime < 0) res.put("hourlyWage", String.valueOf((Integer.parseInt(payValue) / 30) / (workingTime*-1)));
                else res.put("hourlyWage", String.valueOf((Integer.parseInt(payValue) / 30) / workingTime));
            } else if (Integer.parseInt(payValue) > 50000) {
                if (workingTime < 0) res.put("hourlyWage", String.valueOf(Integer.parseInt(payValue) / (workingTime*-1)));
                else res.put("hourlyWage", String.valueOf(Integer.parseInt(payValue) / workingTime));
            } else {
                res.put("hourlyWage", payValue);
            }
        }

        if (sexValue.equals("성별무관")) {
            res.put("sex", "0");
        } else if (sexValue.equals("남자")) {
            res.put("sex", "1");
        } else {
            res.put("sex", "2");
        }

        res.put("address", addressValue);
        res.put("name", titleValue);
        res.put("startTime", workTimeValues[0].substring(0, 2));
        res.put("endTime", workTimeValues[1].substring(0, 2));
        res.put("period", periodValue);
        res.put("days", String.valueOf(workDateValues));
        return res;
    }
}
